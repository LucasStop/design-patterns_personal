package br.pucpr.table.observer;

import br.pucpr.table.Table;
import br.pucpr.table.model.TableData;

public class TableObserver implements Observer {
  private final Table table;

  public TableObserver(Table table) {
    this.table = table;
  }

  @Override
  public void onChange(TableData data) {
    table.setData(data);
    table.print();
  }
}
