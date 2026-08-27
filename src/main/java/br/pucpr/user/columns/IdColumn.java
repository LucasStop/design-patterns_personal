package br.pucpr.user.columns;

import br.pucpr.table.ColumnData;
import br.pucpr.user.User;

public class IdColumn implements ColumnData<User> {
  @Override
  public String getColumnName() {
    return "ID";
  }

  @Override
  public String getValue(User object) {
    return formatId(object.id());
  }

  private static String formatId(Long id) {
    return id != null ? id.toString() : "0";
  }
}
