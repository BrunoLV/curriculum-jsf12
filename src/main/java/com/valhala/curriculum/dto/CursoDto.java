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
		if (!(obj instanceof CursoDto))
			return false;
		CursoDto other = (CursoDto) obj;
		
		return other.nome.equals(this.nome);
	}

	@Override
	public String toString() {
		return "Curso [nome=" + nome + "]";
	}
}
