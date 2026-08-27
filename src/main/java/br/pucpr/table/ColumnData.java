package br.pucpr.table;

public interface ColumnData<T> {
  String getColumnName();

  String getValue(T object);
}
