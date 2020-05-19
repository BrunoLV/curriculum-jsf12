package com.valhala.curriculum.dto.enumerados;

public enum TipoTelefoneDto {
	
	CELULAR("Celular"), FIXO("Fixo");
	
	private String descricao;
	
	private TipoTelefoneDto(final String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
