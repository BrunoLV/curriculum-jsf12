/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.ejb.impl;

import com.valhala.curriculum.dao.CargoDao;
import com.valhala.curriculum.dao.impl.CargoDaoImpl;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.model.Cargo;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author bruno
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CargoServiceBean extends BaseServiceBean implements CargoService {

    private CargoDao cargoDao;

    @PostConstruct
    private void init() {
        this.cargoDao = new CargoDaoImpl(this.manager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cargo salvar(Cargo cargo) {
        this.cargoDao.salvar(cargo);
        return cargo;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Cargo cargo) {
        this.cargoDao.editar(cargo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Cargo cargo) {
        this.cargoDao.deletar(cargo);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Cargo pesquisarPorId(Serializable id) {
        Cargo cargo = this.cargoDao.buscarPorId(id);
        return cargo;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Cargo> buscarTodos() {
        List<Cargo> cargos = this.cargoDao.listarTodos();
        return cargos;
    }

}
