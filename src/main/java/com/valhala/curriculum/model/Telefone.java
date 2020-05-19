package com.valhala.curriculum.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.valhala.curriculum.model.enumerados.TipoTelefone;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_telefone")
@EqualsAndHashCode(callSuper = false)
public class Telefone extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer ddd;
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipo;
	
	private Boolean principal = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "id_dados_pessoais")
	private DadosPessoais dadosPessoais;
	
	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		if (dadosPessoais == null && this.dadosPessoais != null) {
			this.dadosPessoais.getTelefones().remove(this);
		}
		this.dadosPessoais = dadosPessoais;
		if (this.dadosPessoais != null) {
			this.dadosPessoais.getTelefones().add(this);
		}
	}
	
}
