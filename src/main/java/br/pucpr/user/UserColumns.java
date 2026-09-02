package br.pucpr.user;

import br.pucpr.table.model.ColumnData;

public enum UserColumns implements ColumnData<User> {
  ID("%-5s".formatted("ID")) {
    @Override
    public String get(User user) {
      return String.valueOf(user.id());
    }
  },

  NAME("%-10s".formatted("Nome")) {
    @Override
    public String get(User user) {
      return user.name() == null || user.name().isEmpty() ? "NÃO INFORMADO" : user.name();
    }
  },

  EMAIL("%-10s".formatted("Email")) {
    @Override
    public String get(User user) {
      return validateAndFormatEmail(user.email());
    }

    private static String validateAndFormatEmail(String email) {
      return email == null || !email.contains("@") ? "INVÁLIDO" : email;
    }
  },

  CPF("%-10s".formatted("CPF")) {
    @Override
    public String get(User user) {
      return formatCpf(user.cpf());
    }

    private static String formatCpf(String cpf) {
      if (cpf == null || cpf.length() != 11) {
        return "CPF INVÁLIDO";
      }
      return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }
  };

  private final String header;

  UserColumns(String header) {
    this.header = header;
  }

  @Override
  public String header() {
    return header;
  }
}
