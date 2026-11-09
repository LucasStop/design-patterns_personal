package br.pucpr.table;

import br.pucpr.table.model.TableData;

public class PagedTableData implements TableData {
  private final TableData tableData;
  private final int pageSize;
  private int currentPage;

  public PagedTableData(TableData tableData, int pageSize) {
    this.tableData = tableData;
    this.pageSize = pageSize;
    this.currentPage = 0;
  }

  @Override
  public int rowCount() {
    return Math.min(pageSize, tableData.rowCount() - currentPage * pageSize);
  }

  @Override
  public int colCount() {
    return tableData.colCount();
  }

  @Override
  public String header(int col) {
    return tableData.header(col);
  }

  @Override
  public String get(int row, int col) {
    return tableData.get(row + currentPage * pageSize, col);
  }

  public void nextPage() {
    if (currentPage < (tableData.rowCount() - 1) / pageSize) {
      currentPage++;
    }
  }

  public int totalPages() {
    return (int) Math.ceil((double) tableData.rowCount() / pageSize);
  }

  public void previousPage() {
    if (currentPage > 0) {
      currentPage--;
    }
  }
}
