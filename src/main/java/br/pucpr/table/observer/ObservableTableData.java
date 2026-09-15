package br.pucpr.table.observer;

import br.pucpr.table.model.TableData;
import java.util.ArrayList;
import java.util.List;

public class ObservableTableData implements TableData, Observable {
  private final TableData tableData;
  private final List<Observer> observers = new ArrayList<>();

  public ObservableTableData(TableData tableData) {
    this.tableData = tableData;
  }

  @Override
  public int rowCount() {
    return tableData.rowCount();
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
    return tableData.get(row, col);
  }

  @Override
  public void attach(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void detach(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyChange() {
    for (var observer : observers) {
      observer.onChange(this);
    }
  }
}
