package br.pucpr.table.observer;

import br.pucpr.table.model.TableData;

public interface Observer {
  void onChange(TableData data);
}
