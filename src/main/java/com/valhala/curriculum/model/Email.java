package com.valhala.curriculum.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_email")
@EqualsAndHashCode(callSuper = false)
public class Email extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String endereco;
	private Boolean principal = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "id_dados_pessoais")
	private DadosPessoais dadosPessoais;
	
	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		if (dadosPessoais == null && this.dadosPessoais != null) {
			this.dadosPessoais.getEmails().remove(this);
		}
		this.dadosPessoais = dadosPessoais;
		if (this.dadosPessoais != null) {
			this.dadosPessoais.getEmails().add(this);
		}
	}

}
