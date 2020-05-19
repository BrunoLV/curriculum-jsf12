package com.valhala.curriculum.web.mb.crud;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.valhala.curriculum.dto.EmpresaDto;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.model.Empresa;
import com.valhala.curriculum.web.mb.BaseMb;

import lombok.Getter;
import lombok.Setter;

public class EmpresaMb extends BaseMb {

	private static final long serialVersionUID = 5906333509526366720L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

	@EJB
	private EmpresaService service;

	@Getter
	@Setter
	private EmpresaDto empresa;

	@Getter
	@Setter
	private List<EmpresaDto> listaEmpresa;

	ModelMapper modelMapper = new ModelMapper();

	public void deletar() {
		this.service.deletar(modelMapper.map(this.empresa, Empresa.class));
		carregaListaEmpresas();
		inserirMensagemInformativa("Empresa removida com sucesso!!!");
	}

	@PostConstruct
	private void init() {
		Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
		if (id != null && id > 0) {
			this.empresa = modelMapper.map(this.service.pesquisarPorId(id), EmpresaDto.class);
			removeAtributoSessao(ID_EDICAO_SESSAO);
		} else {
			this.empresa = new EmpresaDto();
		}
		carregaListaEmpresas();
	}

	private void carregaListaEmpresas() {
		Type type = new TypeToken<List<EmpresaDto>>() {
		}.getType();
		this.listaEmpresa = modelMapper.map(this.service.buscarTodos(), type);
	}

	public void salvar() {
		Empresa empresaPersistencia = modelMapper.map(this.empresa, Empresa.class);
		if (empresa.getId() == 0) {
			empresa.setId(null);
			this.service.salvar(empresaPersistencia);
			inserirMensagemInformativa("Empresa inserida com sucesso!!!");
		} else {
			this.service.editar(empresaPersistencia);
			inserirMensagemInformativa("Empresa editada com sucesso!!!");
		}
	}

}
