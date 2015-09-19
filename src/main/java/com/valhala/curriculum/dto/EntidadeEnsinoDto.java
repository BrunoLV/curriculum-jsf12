package com.valhala.curriculum.dto;

import java.io.Serializable;

/**
 * Created by bruno on 17/09/15.
 */
public class EntidadeEnsinoDto implements Serializable {

    private Integer id;
    private String nome;

    public EntidadeEnsinoDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
