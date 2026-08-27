package br.pucpr.user.columns;

import br.pucpr.table.ColumnData;
import br.pucpr.user.User;

public class EmailColumn implements ColumnData<User> {
  @Override
  public String getColumnName() {
    return "EMAIL";
  }

  @Override
  public String getValue(User object) {
    return validateAndFormatEmail(object.email());
  }

  private static String validateAndFormatEmail(String email) {
    return email == null || !email.contains("@") ? "INVÁLIDO" : email;
  }
}
