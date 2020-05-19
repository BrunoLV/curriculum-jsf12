package com.valhala.curriculum.ejb;

import java.io.Serializable;

import com.valhala.curriculum.model.DadosPessoais;

public interface DadosPessoaisService extends BaseService<DadosPessoais> {
	
	DadosPessoais buscarPorIdUsario(Serializable idUsario);

}
