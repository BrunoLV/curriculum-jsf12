/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

/**
 * @author bruno
 */
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
