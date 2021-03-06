package com.valhala.curriculum.ejb.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.valhala.curriculum.dao.DadosPessoaisDao;
import com.valhala.curriculum.dao.impl.DadosPessoaisDaoImpl;
import com.valhala.curriculum.ejb.DadosPessoaisService;
import com.valhala.curriculum.model.DadosPessoais;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DadosPessoaisBean extends BaseServiceBean implements DadosPessoaisService {
	
	private DadosPessoaisDao dadosPessoaisDao;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DadosPessoais buscarPorIdUsario(Serializable idUsuario) {
		try {
			return dadosPessoaisDao.buscarPorIdUsario(idUsuario);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<DadosPessoais> buscarTodos() {
		throw new UnsupportedOperationException();
	}

	private void consiste(DadosPessoais dadosPessoais) {
		if (dadosPessoais.getEndereco() != null) {
			dadosPessoais.getEndereco().setDadosPessoais(dadosPessoais);
		}
	}

	@Override
	public void deletar(DadosPessoais t) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(DadosPessoais dadosPessoais) {
		consiste(dadosPessoais);
		dadosPessoaisDao.editar(dadosPessoais);
	}
	
	@PostConstruct
    private void init() {
        dadosPessoaisDao = new DadosPessoaisDaoImpl(this.manager);
    }

	@Override
	public DadosPessoais pesquisarPorId(Serializable id) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DadosPessoais salvar(DadosPessoais dadosPessoais) {
		consiste(dadosPessoais);
		dadosPessoaisDao.salvar(dadosPessoais);
		return dadosPessoais;
	}

}
