package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ColumnTableData<T> implements TableData {
  private final List<ColumnData<? super T>> columns;
  private final List<T> object;

  public ColumnTableData(
      Collection<? extends T> object, Collection<? extends ColumnData<? super T>> columns) {
    this.columns = new ArrayList<>(columns);
    this.object = new ArrayList<>(object);
  }

  @SafeVarargs
  public ColumnTableData(Collection<? extends T> object, ColumnData<? super T>... columns) {
    this(object, Arrays.asList(columns));
  }

  @Override
  public int rowCount() {
    return object.size();
  }

  @Override
  public int colCount() {
    return columns.size();
  }

  @Override
  public String header(int col) {
    return columns.get(col).header();
  }

  @Override
  public String get(int row, int col) {
    var line = object.get(row);
    return columns.get(col).get(line);
  }
}
