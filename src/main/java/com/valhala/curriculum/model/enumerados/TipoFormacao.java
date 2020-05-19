package com.valhala.curriculum.model.enumerados;

public enum TipoFormacao {

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
    
    private TipoFormacao(String descricao) {
    	this.descricao = descricao;
	}
    
    public String getDescricao() {
		return descricao;
	}

}
