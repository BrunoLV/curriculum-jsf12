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
    
	@Override
	public int hashCode() {
		int result = 21;
		result = 31 * result + (nome == null ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Curso))
			return false;
		Curso other = (Curso) obj;
		
		return other.nome.equals(this.nome);
	}

	@Override
	public String toString() {
		return "Curso [nome=" + nome + "]";
	}
    
}
