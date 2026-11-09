package br.pucpr.user;

import br.pucpr.table.reflection.Column;

public record User(
    Long id,
    @Column(header = "    Nome    ") String name,
    @Column(header = "    Email    ") String email,
    @Column(header = "    CPF    ") String cpf) {}
