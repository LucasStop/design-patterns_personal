package br.pucpr.table;

import br.pucpr.user.Theme;
import java.util.List;

public class Table {
  public void print(List<TableData> rows, Theme theme, boolean alignRight) {
    if (rows == null || rows.isEmpty()) {
      System.out.println("ERRO: Lista de dados vazia ou nula.");
      return;
    }

    String[] headers = rows.getFirst().getHeaders();
    int[] widths = new int[headers.length];

    for (var row : rows) {
      if (row == null) {
        continue;
      }
      String[] rowData = row.getRow();
      for (int i = 0; i < rowData.length; i++) {
        if (rowData[i] != null) {
          widths[i] = Math.max(widths[i], headers[i].length());
        }
      }
    }

    final var borderChar = theme.getBorderChar();
    int BORDER_WIDTH = 1; // pipe inicial
    for (int w : widths) {
      BORDER_WIDTH += w + 3; // espaço + largura + espaço + pipe
    }

    StringBuilder sb = new StringBuilder();
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");

    StringBuilder headerFormat = new StringBuilder("|");
    for (int w : widths) {
      headerFormat.append(" %-").append(w).append("s |");
    }

    sb.append(String.format(headerFormat.toString() + "%n", (Object[]) headers));
    sb.append(borderChar.repeat(BORDER_WIDTH)).append("\n");

    for (var row : rows) {
      if (row == null) {
        continue;
      }
      String[] rowData = row.getRow();
      StringBuilder rowFormat = new StringBuilder("|");
      for (int w : widths) {
        rowFormat.append(" %-").append(w).append("s |");
      }

      sb.append(String.format(rowFormat.toString() + "%n", (Object[]) rowData));
    }

    sb.append(borderChar.repeat(BORDER_WIDTH)).append("\n");

    if (alignRight) {
      var lines = sb.toString().split("\n");
      for (var line : lines) {
        System.out.println("                    " + line);
      }
    } else {
      System.out.print(sb);
    }
  }
}
