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
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 21;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EntidadeEnsino)) {
			return false;
		}
		EntidadeEnsino other = (EntidadeEnsino) obj;
	
		return other.nome.equals(this.nome);
	}

	@Override
	public String toString() {
		return "EntidadeEnsino [nome=" + nome + "]";
	}

}
