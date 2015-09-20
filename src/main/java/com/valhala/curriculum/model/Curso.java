package com.valhala.curriculum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by bruno on 16/09/15.
 */
@Entity
@Table(name = "tb_curso")
public class Curso extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    public Curso() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
