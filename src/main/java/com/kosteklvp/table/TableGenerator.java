package com.kosteklvp.table;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.List;
import java.util.OptionalInt;

import org.apache.commons.lang3.StringUtils;

import com.kosteklvp.points.PointsToSymbolsConverter;
import com.kosteklvp.table.header.TableHeader;
import com.kosteklvp.table.row.Row;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TableGenerator {

  private List<Row> rows;

  public String generate() {

    final StringBuilder tableText = new StringBuilder();

//    addBeginning(tableText);

    addHeadings(tableText);

    if (isNotEmpty(rows)) {
      addRows(tableText);
    }

    addEnding(tableText);

    return tableText.toString();

  }

  private int getAdditionalLength() {
    return getLengthOfLongestNameOfPlayers() - TableHeader.PLAYER.getLabel().length();
  }

  private int getLengthOfLongestNameOfPlayers() {
    OptionalInt maxLength = rows.stream().mapToInt(row -> row.getPlayerName().length()).max();

    return maxLength.orElse(0);
  }

  // rows not set exception

  private void addHeadings(StringBuilder tableText) {
    tableText.append(" ").append(StringUtils.repeat("_", 122 + getAdditionalLength())).append("\n");
    tableText.append("| ");
    TableHeader.getAll().forEach(header -> tableText.append(header.getLabel())
        .append(StringUtils.repeat(" ", TableHeader.PLAYER.equals(header) ? getAdditionalLength() : 0)).append(" | "));
    tableText.append("\n");
    tableText.append(StringUtils.repeat(" ̅", 122 + getAdditionalLength())).append("\n");
  }

  private void addRows(StringBuilder tableText) {
    rows.forEach(row -> {
      tableText.append("| ").append(row.getPlayerName()).append(StringUtils.repeat(" ", getLengthOfLongestNameOfPlayers() - row.getPlayerName().length()))
          .append(" |");
      List<Character> symbolPoints = new PointsToSymbolsConverter().convert(row.getPoints());
      symbolPoints.forEach(symbol -> tableText.append("  ").append(symbol).append(" |"));
      tableText.append("\n");
    });
  }

  private void addEnding(StringBuilder tableText) {
    tableText.append(" ").append(StringUtils.repeat("_", 122 + getAdditionalLength()));
  }

}
