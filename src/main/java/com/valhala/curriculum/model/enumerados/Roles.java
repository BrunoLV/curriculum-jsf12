package com.valhala.curriculum.model.enumerados;

public enum Roles {

    USER("USER"), ADMIN("ADMIN");

    private final String nome;

    private Roles(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
