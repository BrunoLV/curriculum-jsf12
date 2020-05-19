package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "dadosPessoais")
	private Endereco endereco;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dadosPessoais")
	private List<Email> emails = new ArrayList<Email>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dadosPessoais")
	private List<Telefone> telefones = new ArrayList<Telefone>();

	@OneToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

}
