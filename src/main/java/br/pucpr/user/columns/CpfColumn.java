package br.pucpr.user.columns;

import br.pucpr.table.ColumnData;
import br.pucpr.user.User;

public class CpfColumn implements ColumnData<User> {
  @Override
  public String getColumnName() {
    return "CPF";
  }

  @Override
  public String getValue(User object) {
    return formatCpf(object.cpf());
  }

  private static String formatCpf(String cpf) {
    if (cpf == null || cpf.length() != 11) {
      return "CPF INVÁLIDO";
    }
    return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
  }
}
