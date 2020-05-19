package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

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
@Table(name = "tb_curriculo")
@EqualsAndHashCode(callSuper = false)
public class Curriculo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3875979851399236476L;

	@OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

	@EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "curriculo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ExperienciaProfissional> experienciasProfissionais = new LinkedHashSet<ExperienciaProfissional>();

	@EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "curriculo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<FormacaoAcademica> formacoesAcademicas = new LinkedHashSet<FormacaoAcademica>();

}
