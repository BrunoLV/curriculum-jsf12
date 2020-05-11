package com.valhala.curriculum.web.mb.crud;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import com.valhala.curriculum.dto.RolesDto;
import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.mappers.UsuarioMapper;
import com.valhala.curriculum.web.mb.BaseMb;

public class UsuarioMb extends BaseMb {

    private static final long serialVersionUID = -3564941728014454592L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    private UsuarioDto usuario;
    private List<UsuarioDto> listaUsuario;
    
    private List<SelectItem> papeis = new ArrayList<SelectItem>();

    public List<SelectItem> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<SelectItem> papeis) {
		this.papeis = papeis;
	}

	private UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @EJB
    private UsuarioService service;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.usuario = usuarioMapper.usuarioToUsuarioDto(this.service.pesquisarPorId(id));
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.usuario = new UsuarioDto();
            this.usuario.setPapeis(new ArrayList<RolesDto>());
        }
        this.listaUsuario = usuarioMapper.listaUsuarioToListaUsuarioDto(this.service.buscarTodos());
        
        papeis = new ArrayList<SelectItem>();
        for (RolesDto rolesDto : RolesDto.values()) {
			papeis.add(new SelectItem(rolesDto, rolesDto.getNome()));
		}
    }

    public void salvar() {
        if (this.usuario.getId() == 0) {
            usuario.setId(null);
            this.service.salvar(usuarioMapper.usuarioDtoToUsuario(usuario));
            inserirMensagemInformativa("Usuario inserido com sucesso!!!");
        } else {
            this.service.editar(usuarioMapper.usuarioDtoToUsuario(usuario));
            inserirMensagemInformativa("Usuario editado com sucesso!!!");
        }
    }

    public void deletar() {
        this.service.deletar(usuarioMapper.usuarioDtoToUsuario(usuario));
        this.listaUsuario = usuarioMapper.listaUsuarioToListaUsuarioDto(this.service.buscarTodos());
        inserirMensagemInformativa("Usuario removido com sucesso!!!");
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioDto> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<UsuarioDto> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

}
