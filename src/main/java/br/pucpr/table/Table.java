package br.pucpr.table;

import br.pucpr.user.Theme;
import java.util.List;

public class Table {
  public void print(List<TableData> rows, Theme theme, boolean alignRight) {
    if (rows == null || rows.isEmpty()) {
      System.out.println("ERRO: Lista de dados vazia ou nula.");
      return;
    }

    // ===== Calculando a largura de cada coluna =====
    String[] headers = rows.getFirst().getHeaders();
    int[] widths = new int[headers.length];

    for (var row : rows) {
      if (row == null) {
        continue;
      }
      String[] rowData = row.getRow();
      for (int i = 0; i < rowData.length; i++) {
        if (rowData[i] != null) {
          widths[i] = Math.max(widths[i], Math.max(headers[i].length(), rowData[i].length()));
        }
      }
    }

    // ===== Borda (topo/meio/rodapé da tabela) =====
    final var borderChar = theme.getBorderChar();
    int borderWidth = 1; // pipe inicial
    for (int w : widths) {
      borderWidth += w + 3; // espaço + largura + espaço + pipe
    }

    StringBuilder sb = new StringBuilder();
    sb.repeat(borderChar, borderWidth).append("\n");

    // ===== Cabeçalho (nomes das colunas) =====
    StringBuilder headerFormat = new StringBuilder("|");
    for (int w : widths) {
      headerFormat.append(" %-").append(w).append("s |");
    }

    String rowFormat = headerFormat + "%n";

    sb.append(String.format(rowFormat, (Object[]) headers));
    sb.repeat(borderChar, borderWidth).append("\n");

    // ===== Linhas de dados (uma por TableData da lista) =====
    for (var row : rows) {
      if (row == null) {
        continue;
      }
      String[] rowData = row.getRow();

      sb.append(String.format(rowFormat, (Object[]) rowData));
    }

    sb.repeat(borderChar, borderWidth).append("\n");

    // ===== Saída (alinhada à direita ou direto no console) =====
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
