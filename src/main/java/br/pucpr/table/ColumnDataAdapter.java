package br.pucpr.table;

import java.util.ArrayList;
import java.util.List;

public class ColumnDataAdapter<T> implements TableData {
  private final T object;
  private final List<ColumnData<? super T>> columns;

  public ColumnDataAdapter(T object, List<ColumnData<? super T>> columns) {
    this.object = object;
    this.columns = new ArrayList<>(columns);
  }

  public String[] getHeaders() {
    return columns.stream().map(ColumnData::getColumnName).toArray(String[]::new);
  }

  public String[] getRow() {
    return columns.stream().map(c -> c.getValue(object)).toArray(String[]::new);
  }
}
