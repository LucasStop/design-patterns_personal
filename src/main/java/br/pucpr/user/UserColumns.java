package br.pucpr.user;

import br.pucpr.table.model.ColumnData;
import java.util.function.Function;

public enum UserColumns implements ColumnData<User> {
  ID("%-5s".formatted("ID"), user -> String.valueOf(user.id())),
  NAME(
      "%-15s".formatted("Nome"),
      user -> user.name() == null || user.name().isEmpty() ? "NÃO INFORMADO" : user.name()),
  EMAIL("%-15s".formatted("Email"), user -> validateAndFormatEmail(user.email())),
  CPF("%-15s".formatted("CPF"), user -> formatCpf(user.cpf()));

  private final String header;
  private final Function<User, String> getter;

  UserColumns(String header, Function<User, String> getter) {
    this.header = header;
    this.getter = getter;
  }

  @Override
  public String header() {
    return header;
  }

  @Override
  public String get(User user) {
    return getter.apply(user);
  }

  private static String validateAndFormatEmail(String email) {
    return email == null || !email.contains("@") ? "INVÁLIDO" : email;
  }

  private static String formatCpf(String cpf) {
    if (cpf == null || cpf.length() != 11) {
      return "CPF INVÁLIDO";
    }
    return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
  }
}
