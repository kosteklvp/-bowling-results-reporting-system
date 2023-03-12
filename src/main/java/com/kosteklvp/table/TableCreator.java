package com.kosteklvp.table;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.OptionalInt;

import org.apache.commons.lang3.StringUtils;

import com.kosteklvp.bowling.Game;
import com.kosteklvp.calculator.ScoreCalculator;
import com.kosteklvp.converter.PointsToSymbolsConverter;
import com.kosteklvp.table.header.TableHeader;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
public class TableCreator {

  private static final int TABLE_LENGTH = 121;

  private Game game;

  private final ScoreCalculator scoreCalculator = new ScoreCalculator();

  public String create() {

    final StringBuilder tableText = new StringBuilder();

    addHeaders(tableText);

    addRows(tableText);

    addEnding(tableText);

    return tableText.toString();
  }

  private void addHeaders(StringBuilder tableText) {
    tableText.append(" ").append(StringUtils.repeat("_", TABLE_LENGTH + getAdditionalLength())).append("\n");
    tableText.append("| ");
    TableHeader.getAll().forEach(header -> tableText.append(header.getLabel())
        .append(StringUtils.repeat(" ", TableHeader.PLAYER.equals(header) ? getAdditionalLength() : 0)).append(" | "));
    tableText.append("\n");
    tableText.append(StringUtils.repeat(" ̅", TABLE_LENGTH + getAdditionalLength())).append("\n");
  }

  private void addRows(StringBuilder tableText) {
    game.getPlayerPlays().forEach(playerPlay -> {
      tableText.append("| ").append(playerPlay.getPlayerName())
          .append(StringUtils.repeat(" ", getLengthOfLongestNameOfPlayers() - playerPlay.getPlayerName().length())).append(" | ");
      List<Character> symbolPoints = new PointsToSymbolsConverter().convert(playerPlay.getAllRolls().stream().map(t -> t.getNumberOfKnockedPins()).toList());
      symbolPoints.forEach(symbol -> tableText.append(" ").append(symbol).append(" | "));
      String score = String.valueOf(scoreCalculator.calculate(playerPlay));

      tableText.append(StringUtils.repeat(" ", 4 - score.length())).append(score).append("  |");
      tableText.append("\n");
    });
  }

  private void addEnding(StringBuilder tableText) {
    tableText.append(" ").append(StringUtils.repeat(" ̅", TABLE_LENGTH + getAdditionalLength()));
  }

  private int getAdditionalLength() {
    return getLengthOfLongestNameOfPlayers() - TableHeader.PLAYER.getLabel().length();
  }

  private int getLengthOfLongestNameOfPlayers() {
    OptionalInt maxLength = game.getPlayerPlays().stream().mapToInt(row -> row.getPlayerName().length()).max();

    return maxLength.orElse(0);
  }

  public static TableCreator of(Game game) {
    TableCreator table = new TableCreator();
    table.setGame(game);

    return table;
  }

}
