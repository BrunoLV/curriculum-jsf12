package com.valhala.curriculum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by bruno on 16/09/15.
 */
@Entity
@Table(name = "tb_entidade_ensino")
public class EntidadeEnsino extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8567451816869419417L;
	
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    public EntidadeEnsino() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
