package com.valhala.curriculum.model.enumerados;

public enum TipoTelefone {
	
	CELULAR("Celular"), FIXO("Fixo");
	
	private String descricao;
	
	private TipoTelefone(final String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
