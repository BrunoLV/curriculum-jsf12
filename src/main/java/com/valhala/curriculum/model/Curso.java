package com.valhala.curriculum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_curso")
@EqualsAndHashCode(callSuper = false)
public class Curso extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false, unique = true)
    private String nome;
    
}
