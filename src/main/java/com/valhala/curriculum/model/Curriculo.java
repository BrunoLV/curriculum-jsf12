/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 */
@Entity
@Table(name = "tb_curriculo")
public class Curriculo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3875979851399236476L;

	@ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL)
    private List<ExperienciaProfissional> listaExperienciaProfissional = new ArrayList<ExperienciaProfissional>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "curriculo", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<FormacaoAcademica> listaFormacaoAcademica = new ArrayList<FormacaoAcademica>();

    public Curriculo() {
    }

    public Curriculo(Integer id, Usuario usuario, List<ExperienciaProfissional> listaExperienciaProfissional) {
        this.id = id;
        this.usuario = usuario;
        this.listaExperienciaProfissional = listaExperienciaProfissional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ExperienciaProfissional> getListaExperienciaProfissional() {
        return listaExperienciaProfissional;
    }

    public void setListaExperienciaProfissional(List<ExperienciaProfissional> experienciasProfissionais) {
        this.listaExperienciaProfissional = experienciasProfissionais;
    }

    public List<FormacaoAcademica> getListaFormacaoAcademica() {
        return listaFormacaoAcademica;
    }

    public void setListaFormacaoAcademica(List<FormacaoAcademica> listaFormacaoAcademica) {
        this.listaFormacaoAcademica = listaFormacaoAcademica;
    }

    public void adicionarExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
        if (experienciaProfissional != null && !getListaExperienciaProfissional().contains(experienciaProfissional)) {
            getListaExperienciaProfissional().add(experienciaProfissional);
            experienciaProfissional.setCurriculo(this);
        }
    }

    public void removerExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
        if (experienciaProfissional != null) {
            getListaExperienciaProfissional().remove(experienciaProfissional);
            experienciaProfissional.setCurriculo(null);
        }
    }

    public void adicionarFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        if (formacaoAcademica != null && !getListaFormacaoAcademica().contains(formacaoAcademica)) {
            getListaFormacaoAcademica().add(formacaoAcademica);
            formacaoAcademica.setCurriculo(this);
        }
    }

    public void removerFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        if (formacaoAcademica != null) {
            getListaFormacaoAcademica().remove(formacaoAcademica);
            formacaoAcademica.setCurriculo(null);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curriculo other = (Curriculo) obj;
        if (!this.usuario.equals(other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curriculo{" + "id=" + id + ", usuario=" + usuario + '}';
    }

}
