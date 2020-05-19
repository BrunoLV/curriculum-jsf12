package com.valhala.curriculum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_cargo")
@EqualsAndHashCode(callSuper = false)
public class Cargo extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nome", unique = true, nullable = false)
    private String nome;

}
