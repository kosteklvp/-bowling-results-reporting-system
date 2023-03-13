package com.kosteklvp.table;

import static lombok.AccessLevel.PRIVATE;

import org.apache.commons.lang3.StringUtils;

import com.kosteklvp.bowling.Game;
import com.kosteklvp.bowling.PlayerPlay;
import com.kosteklvp.calculator.ScoreCalculator;
import com.kosteklvp.table.header.TableHeader;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
public class TableCreator {

  private Game game;

  private final ScoreCalculator scoreCalculator = new ScoreCalculator();

  private final StringBuilder tableText = new StringBuilder();

  public String create() {
    createHeaders();

    createRows();

    createEnding();

    return tableText.toString();
  }

  private void createHeaders() {
    tableText.append(" ").append(StringUtils.repeat("_", getTableLength())).append("\n");
    tableText.append("| ");
    TableHeader.getAll().forEach(header -> tableText.append(header.getLabel())
        .append(StringUtils.repeat(" ", TableHeader.PLAYER.equals(header) ? getAdditionalLengthAfterPlayerHeader() : 0)).append(" | "));
    tableText.append("\n").append(StringUtils.repeat(" ̅", getTableLength())).append("\n");
  }

  private void createRows() {
    game.getPlayerPlays().forEach(this::createRow);
  }

  private void createRow(PlayerPlay playerPlay) {
    tableText.append("| ").append(playerPlay.getPlayerName()).append(getAdditionalSpaceAfterPlayerName(playerPlay.getPlayerName())).append(" | ");
    playerPlay.getAllSymbols().forEach(symbol -> tableText.append(" ").append(symbol).append(" | "));

    int score = scoreCalculator.calculate(playerPlay);
    tableText.append(getAdditionalSpaceBeforeScore(score)).append(score).append("  |").append("\n");
  }

  private void createEnding() {
    tableText.append(" ").append(StringUtils.repeat("‾", getTableLength()));
  }

  private String getAdditionalSpaceBeforeScore(int score) {
    final int MAX_SPACE = 4;
    return StringUtils.repeat(" ", MAX_SPACE - String.valueOf(score).length());
  }

  private String getAdditionalSpaceAfterPlayerName(String playerName) {
    return StringUtils.repeat(" ", getAdditionalLengthAfterPlayerName(playerName));
  }

  private int getTableLength() {
    final int DEFAULT_TABLE_LENGTH = 121;
    return DEFAULT_TABLE_LENGTH + getAdditionalLengthAfterPlayerHeader();
  }

  private int getAdditionalLengthAfterPlayerHeader() {
    if (getLengthOfLongestNameOfPlayers() < TableHeader.PLAYER.getLabel().length()) {
      return 0;
    }

    return getLengthOfLongestNameOfPlayers() - TableHeader.PLAYER.getLabel().length();
  }

  private int getAdditionalLengthAfterPlayerName(String playerName) {
    if (playerName.length() > TableHeader.PLAYER.getLabel().length() ||
        getLengthOfLongestNameOfPlayers() > TableHeader.PLAYER.getLabel().length()) {
      return getLengthOfLongestNameOfPlayers() - playerName.length();
    } else {
      return TableHeader.PLAYER.getLabel().length() - playerName.length();
    }
  }

  private int getLengthOfLongestNameOfPlayers() {
    return game.getPlayerPlays().stream().mapToInt(row -> row.getPlayerName().length()).max().orElse(0);
  }

  public static TableCreator of(Game game) {
    TableCreator table = new TableCreator();
    table.setGame(game);

    return table;
  }

}
