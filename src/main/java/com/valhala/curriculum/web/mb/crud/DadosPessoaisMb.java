package com.valhala.curriculum.web.mb.crud;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.modelmapper.ModelMapper;

import com.valhala.curriculum.dto.DadosPessoaisDto;
import com.valhala.curriculum.dto.EnderecoDto;
import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.dto.enumerados.UFDto;
import com.valhala.curriculum.ejb.DadosPessoaisService;
import com.valhala.curriculum.model.DadosPessoais;
import com.valhala.curriculum.model.Usuario;
import com.valhala.curriculum.web.mb.BaseMb;
import com.valhala.curriculum.web.mb.controle.ControleSessaoMb;

import lombok.Getter;
import lombok.Setter;

public class DadosPessoaisMb extends BaseMb {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private DadosPessoaisService dadosPessoaisService;

	@Getter
	@Setter
	private ControleSessaoMb controleSessaoMb;
	
	@Getter
	@Setter
	private DadosPessoaisDto dadosPessoais;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@PostConstruct
	private void init() {
		
		UsuarioDto usuarioLogado = controleSessaoMb.obtemUsuarioLogado();
		
		DadosPessoais dados = dadosPessoaisService.buscarPorIdUsario(usuarioLogado.getId());
		if (dados != null) {
			dadosPessoais = modelMapper.map(dados, DadosPessoaisDto.class);
		} else {
			dadosPessoais = new DadosPessoaisDto();
			dadosPessoais.setEndereco(new EnderecoDto());
		}
		
	}
	
	public List<SelectItem> getEstados() {
		UFDto[] estados = UFDto.values();
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for (UFDto uf : estados) {
			itens.add(new SelectItem(uf));
		}
		return itens;
	}
	
	public void salvar() {
		DadosPessoais dadosPessoaisPersistencia = modelMapper.map(dadosPessoais, DadosPessoais.class);
		UsuarioDto usuario = controleSessaoMb.obtemUsuarioLogado();
		dadosPessoaisPersistencia.setUsuario(modelMapper.map(usuario, Usuario.class));
		if (dadosPessoais.getId() == null) {
			this.dadosPessoaisService.salvar(dadosPessoaisPersistencia);
			inserirMensagemInformativa("Dados Pessoais inseridos com sucesso!!!");
		} else {
			this.dadosPessoaisService.editar(dadosPessoaisPersistencia);
			inserirMensagemInformativa("Dados Pessoais atualizados com sucesso!!!");
		}
	}
	
}
