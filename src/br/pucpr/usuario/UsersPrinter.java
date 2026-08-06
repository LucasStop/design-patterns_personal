package br.pucpr.usuario;

import java.util.ArrayList;

import static br.pucpr.usuario.Theme.*;

public class UsersPrinter {
    public void print(ArrayList<User> users, boolean maskCpf, boolean alignRight, Theme theme) {
        if (users == null || users.isEmpty()) {
            System.out.println("ERRO: Lista de usuários vazia ou nula.");
            return;
        }
        final var borderChar = theme.getBorderChar();

        // Borda superior e cabeçalho
        var sb = new StringBuilder();
        sb.repeat(borderChar, 74).append("\n");
        sb.append(String.format("| %-5s | %-20s | %-22s | %-14s |\n", "ID", "NOME", "EMAIL", "CPF"));
        sb.repeat(borderChar, 74).append("\n");
        for (var user : users) {
            if (user == null) {
                continue;
            }
            sb.append(String.format("| %-5s | %-20s | %-22s | %-14s |\n",
                    formatId(user),
                    formatName(user),
                    validateAndFormatEmail(user),
                    formatCpf(user, maskCpf)
            ));

            //Borda inferior
            sb.repeat(borderChar, 74).append("\n");

            //Espaçamento
            if (alignRight) {
                var lines = sb.toString().split("\n");
                for (var line : lines) {
                    System.out.println("                    " + line);
                }
            } else {
                System.out.print(sb);
            }
        }
    }

    private static String formatId(User user) {
        return user.id() != null ? user.id().toString() : "0";
    }

    private static String formatCpf(User user, boolean maskCpf) {
        var cpf = user.cpf();
        if (cpf != null && cpf.length() == 11) {
            if (maskCpf) {
                cpf = "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
            } else {
                cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            }
        } else {
            cpf = "CPF INVALIDO";
        }
        return cpf;
    }

    private static String validateAndFormatEmail(User user) {
        var email = user.email();
        if (email == null || !email.contains("@")) {
            email = "INVALIDO";
        }
        return email;
    }

    private static String formatName(User user) {
        var name = user.name();
        if (name == null || name.isEmpty()) {
            name = "NÃO INFORMADO";
        } else if (name.length() > 20) {
            name = name.substring(0, 17) + "...";
        }
        return name;
    }

    public static void main(String[] args) {
        var usuarios = new ArrayList<User>();
        usuarios.add(new User(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
        usuarios.add(new User(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
        usuarios.add(new User(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
        usuarios.add(new User(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
        usuarios.add(new User(105L, "Lucas Mendes", "lucas@email.com", "12345"));
        usuarios.add(new User(106L, "", "beatriz@email.com", "55566677788"));

        var printer = new UsersPrinter();
        printer.print(usuarios, true, true, LIGHT);
    }
}