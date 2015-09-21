package com.valhala.curriculum.dto;

import java.io.Serializable;

/**
 * Created by bruno on 17/09/15.
 */
public class EntidadeEnsinoDto implements Serializable {

    private static final long serialVersionUID = -2254023025622888141L;
	
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
		if (!(obj instanceof EntidadeEnsinoDto)) {
			return false;
		}
		EntidadeEnsinoDto other = (EntidadeEnsinoDto) obj;
	
		return other.nome.equals(this.nome);
	}

	@Override
	public String toString() {
		return "EntidadeEnsino [nome=" + nome + "]";
	}
}
