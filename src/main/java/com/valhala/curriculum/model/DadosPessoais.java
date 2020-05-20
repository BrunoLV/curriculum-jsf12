package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_dados_pessoais")
@EqualsAndHashCode(callSuper = false)
public class DadosPessoais extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String titulo;
	private String email;
	private String telefone;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "dadosPessoais")
	private Endereco endereco;

	@OneToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

}
