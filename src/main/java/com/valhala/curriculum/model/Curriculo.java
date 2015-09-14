/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL)
    private List<ExperienciaProfissional> experienciasProfissionais = new ArrayList<ExperienciaProfissional>();

    public Curriculo() {
    }

    public Curriculo(Integer id, Usuario usuario, List<ExperienciaProfissional> experienciasProfissionais) {
        this.id = id;
        this.usuario = usuario;
        this.experienciasProfissionais = experienciasProfissionais;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ExperienciaProfissional> getExperienciasProfissionais() {
        return experienciasProfissionais;
    }

    public void setExperienciasProfissionais(List<ExperienciaProfissional> experienciasProfissionais) {
        this.experienciasProfissionais = experienciasProfissionais;
    }

    public void adicionarExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
        getExperienciasProfissionais().add(experienciaProfissional);
        experienciaProfissional.setCurriculo(this);
    }

    public void removerExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
        getExperienciasProfissionais().remove(experienciaProfissional);
        experienciaProfissional.setCurriculo(null);
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
