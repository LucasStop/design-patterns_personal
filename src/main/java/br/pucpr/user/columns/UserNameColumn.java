package br.pucpr.user.columns;

import br.pucpr.table.ColumnData;
import br.pucpr.user.User;

public class UserNameColumn implements ColumnData<User> {
  @Override
  public String getColumnName() {
    return "NOME";
  }

  @Override
  public String getValue(User object) {
    return formatName(object);
  }

  private static String formatName(User user) {
    var name = user.name();
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }
    if (name.length() > 20) {
      name = name.substring(0, 17) + "...";
    }
    return name;
  }
}
