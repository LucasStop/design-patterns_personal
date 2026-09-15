package br.pucpr.table.observer;

public interface Observable {
  void attach(Observer observer);

  void detach(Observer observer);

  void notifyChange();
}
