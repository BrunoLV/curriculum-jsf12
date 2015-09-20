/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dto;

import java.io.Serializable;

/**
 * @author bruno
 */
public class CargoDto implements Serializable {

    private static final long serialVersionUID = -747323525388389196L;
	
    private Integer id;
    private String nome;

    public CargoDto() {
        super();
    }

    public CargoDto(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
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
