package com.valhala.curriculum.dto.enumerados;

public enum RolesDto {
	
    USER("USER"), ADMIN("ADMIN");

    private final String nome;

    private RolesDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
