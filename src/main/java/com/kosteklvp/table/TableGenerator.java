package com.kosteklvp.table;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.List;

import com.kosteklvp.table.heading.Heading;
import com.kosteklvp.table.heading.HeadingsNotSetException;
import com.kosteklvp.table.row.Row;

import lombok.Builder;

@Builder
public class TableGenerator {

  private List<Heading> headings;

  private List<Row> rows;

  public String generate() {
    if (isEmpty(headings)) {
      throw new HeadingsNotSetException();
    }

    final StringBuilder tableText = new StringBuilder();

    addBeginning(tableText);

    addHeadings(tableText);

    if (isNotEmpty(rows)) {
      addRows(tableText);
    }

    addEnding(tableText);

    return tableText.toString();

  }

  private void addHeadingsTop(StringBuilder tableText) {
    tableText.append("-----------").append("\n");
  }

  private void addHeadings(StringBuilder tableText) {
    tableText.append("-----------").append("\n");
    tableText.append(" | ");
    headings.forEach(heading -> tableText.append(heading.getLabel()).append(" | "));
    tableText.append("\n");
    tableText.append("-----------").append("\n");
  }

  private void addRows(StringBuilder tableText) {
    rows.forEach(row -> {
      tableText.append(" | ").append(row.getPlayerName()).append(" | ");
      row.getValues().forEach(value -> tableText.append(value).append(" | "));
      tableText.append("\n");
    });
  }

  private void addEnding(StringBuilder tableText) {
    tableText.append("\n").append("\n").append("&#x200B;");
  }

}
