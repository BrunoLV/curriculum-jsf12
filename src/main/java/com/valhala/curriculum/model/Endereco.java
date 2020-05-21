package com.valhala.curriculum.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.valhala.curriculum.model.enumerados.UF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_endereco")
@EqualsAndHashCode(callSuper = false)
public class Endereco extends BaseEntity implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	private UF estado;
	
	@OneToOne
	@JoinColumn(name = "id_dados_pessoais", nullable = false)
	private DadosPessoais dadosPessoais;

	public String completo() {
		return logradouro + ", " + numero + " - " + bairro + " - " + cidade + "/" + estado;
	} 

}
