package com.valhala.curriculum.web.mb.crud;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.valhala.curriculum.dto.CargoDto;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.model.Cargo;
import com.valhala.curriculum.web.mb.BaseMb;

import lombok.Getter;
import lombok.Setter;

public class CargoMb extends BaseMb {

	private static final long serialVersionUID = 4240025875447639696L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

	@EJB
	private CargoService cargoService;

	@Getter
	@Setter
	private CargoDto cargo;

	@Getter
	@Setter
	private List<CargoDto> listaCargo;

	ModelMapper modelMapper = new ModelMapper();

	public void deletar() {
		Cargo entity = modelMapper.map(cargo, Cargo.class);
		this.cargoService.deletar(entity);
		carregaListaCargos();
		inserirMensagemInformativa("Cargo removido com sucesso!!!");
	}

	@PostConstruct
	private void init() {
		Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
		if (id != null && id > 0) {
			this.cargo = modelMapper.map(this.cargoService.pesquisarPorId(id), CargoDto.class);
			removeAtributoSessao(ID_EDICAO_SESSAO);
		} else {
			this.cargo = new CargoDto();
		}
		carregaListaCargos();
	}

	private void carregaListaCargos() {
		Type type = new TypeToken<List<CargoDto>>() {
		}.getType();
		this.listaCargo = modelMapper.map(this.cargoService.buscarTodos(), type);
	}

	public void salvar() {
		if (cargo.getId() == 0) {
			cargo.setId(null);
			this.cargoService.salvar(modelMapper.map(cargo, Cargo.class));
			inserirMensagemInformativa("Cargo inserido com sucesso!!!");
		} else {
			this.cargoService.editar(modelMapper.map(cargo, Cargo.class));
			inserirMensagemInformativa("Cargo editado com sucesso!!!");
		}
	}

}
