package com.valhala.curriculum.web.mb.crud;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.dto.enumerados.RolesDto;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.Usuario;
import com.valhala.curriculum.web.mb.BaseMb;

import lombok.Getter;
import lombok.Setter;

public class UsuarioMb extends BaseMb {

    private static final long serialVersionUID = -3564941728014454592L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

	@Getter
	@Setter
    private UsuarioDto usuario;
    
	@Getter
	@Setter
	private List<UsuarioDto> listaUsuario;
    
	@Getter
	@Setter
    private List<SelectItem> papeis = new ArrayList<SelectItem>();

	@EJB
    private UsuarioService service;
	
	ModelMapper modelMapper = new ModelMapper();

	public void deletar() {
        this.service.deletar(modelMapper.map(usuario, Usuario.class));
        carregaListaUsuarios();
        inserirMensagemInformativa("Usuario removido com sucesso!!!");
    }

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.usuario = modelMapper.map(this.service.pesquisarPorId(id), UsuarioDto.class);
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.usuario = new UsuarioDto();
            this.usuario.setPapeis(new ArrayList<RolesDto>());
        }
        carregaListaUsuarios();
        
        for (RolesDto r : RolesDto.values()) {
			papeis.add(new SelectItem(r, r.getNome()));
		}
        
    }

	private void carregaListaUsuarios() {
		Type type = new TypeToken<List<UsuarioDto>>() {}.getType();
        this.listaUsuario = modelMapper.map(this.service.buscarTodos(), type);
	}

    public void salvar() {
        Usuario usuarioPersistencia = modelMapper.map(usuario, Usuario.class);
		if (this.usuario.getId() == 0) {
            usuario.setId(null);
            this.service.salvar(usuarioPersistencia);
            inserirMensagemInformativa("Usuario inserido com sucesso!!!");
        } else {
            this.service.editar(usuarioPersistencia);
            inserirMensagemInformativa("Usuario editado com sucesso!!!");
        }
    }

}
