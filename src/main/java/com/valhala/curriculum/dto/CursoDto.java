package com.valhala.curriculum.dto;

import java.io.Serializable;

/**
 * Created by bruno on 17/09/15.
 */
public class CursoDto implements Serializable {

    private static final long serialVersionUID = 8648671112829745049L;
	
    private Integer id;
    private String nome;

    public CursoDto() {
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
