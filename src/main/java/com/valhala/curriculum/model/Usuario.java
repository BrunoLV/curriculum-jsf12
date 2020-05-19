package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import com.valhala.curriculum.model.enumerados.Roles;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_usuario")
@EqualsAndHashCode(callSuper = false)
public class Usuario extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false)
    private String nome;
   
	@Column(name = "email", unique = true, nullable = false)
    private String email;
    
	@Column(name = "senha", unique = true, nullable = false)
    private String senha;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_papeis_usuario", joinColumns = @JoinColumn(name = "id_usuario"))
    @Column(name = "papel", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Roles> papeis = new ArrayList<Roles>();

}
