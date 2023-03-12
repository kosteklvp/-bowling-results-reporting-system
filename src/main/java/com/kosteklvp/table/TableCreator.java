package com.kosteklvp.table;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.OptionalInt;

import org.apache.commons.lang3.StringUtils;

import com.kosteklvp.bowling.Game;
import com.kosteklvp.converter.PointsToSymbolsConverter;
import com.kosteklvp.table.header.TableHeader;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = PRIVATE)
@Setter(PRIVATE)
public class TableCreator {

  private static final int TABLE_LENGTH = 122;

  private Game game;

  public String create() {

    final StringBuilder tableText = new StringBuilder();

    addHeaders(tableText);

    addRows(tableText);

    addEnding(tableText);

    return tableText.toString();
  }

  private void addHeaders(StringBuilder tableText) {
    tableText.append(" ").append(StringUtils.repeat("_", 122 + getAdditionalLength())).append("\n");
    tableText.append("| ");
    TableHeader.getAll().forEach(header -> tableText.append(header.getLabel())
        .append(StringUtils.repeat(" ", TableHeader.PLAYER.equals(header) ? getAdditionalLength() : 0)).append(" | "));
    tableText.append("\n");
    tableText.append(StringUtils.repeat(" ̅", 122 + getAdditionalLength())).append("\n");
  }

  private void addRows(StringBuilder tableText) {
    game.getPlayerPlays().forEach(row -> {
      tableText.append("| ").append(row.getPlayerName())
          .append(StringUtils.repeat(" ", getLengthOfLongestNameOfPlayers() - row.getPlayerName().length())).append(" |");
      List<Character> symbolPoints = new PointsToSymbolsConverter().convert(row.getAllRolls().stream().map(d -> {

        return d != null ? d.getNumberOfKnockedPins() : ' ';
      }).toList());
      symbolPoints.forEach(symbol -> tableText.append("  ").append(symbol).append(" |"));
      tableText.append("\n");
    });
  }

  private void addEnding(StringBuilder tableText) {
    tableText.append(" ").append(StringUtils.repeat("_", 122 + getAdditionalLength()));
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
