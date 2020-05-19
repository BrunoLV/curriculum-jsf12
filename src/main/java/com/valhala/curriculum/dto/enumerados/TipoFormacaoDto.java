package com.valhala.curriculum.dto.enumerados;

public enum TipoFormacaoDto {

	CURSO_LIVRE("Curso Livre"),
    TECNICO("Técnico"),
    SUPERIOR_BACHARELADO("Superior Bacharelado"),
    SUPERIOR_LICENCIATURA("Superior Licenciatura"),
    POS_GRADUACAO("Pós Graduação"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado"),
    POS_DOUTORADO("Pós Doutorado"),
    MBA("MBA");
    
    private String descricao;
    
    private TipoFormacaoDto(String descricao) {
    	this.descricao = descricao;
	}
    
    public String getDescricao() {
		return descricao;
	}

}
