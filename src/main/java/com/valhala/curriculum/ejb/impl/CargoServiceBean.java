package com.valhala.curriculum.ejb.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.valhala.curriculum.dao.CargoDao;
import com.valhala.curriculum.dao.impl.CargoDaoImpl;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.model.Cargo;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CargoServiceBean extends BaseServiceBean implements CargoService {

    private CargoDao cargoDao;

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Cargo> buscarTodos() {
        List<Cargo> cargos = this.cargoDao.listarTodos();
        return cargos;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Cargo cargo) {
        this.cargoDao.deletar(cargo);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Cargo cargo) {
        this.cargoDao.editar(cargo);
    }

    @PostConstruct
    private void init() {
        this.cargoDao = new CargoDaoImpl(this.manager);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Cargo pesquisarPorId(Serializable id) {
        Cargo cargo = this.cargoDao.buscarPorId(id);
        return cargo;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cargo salvar(Cargo cargo) {
        this.cargoDao.salvar(cargo);
        return cargo;
    }

}
