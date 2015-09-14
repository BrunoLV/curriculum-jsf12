package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.UsuarioDTO;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.Usuario;
import com.valhala.curriculum.utils.api.Parser;
import com.valhala.curriculum.utils.impl.ParserUsuarioToUsuarioDTO;
import com.valhala.curriculum.web.mb.BaseMB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

public class UsuarioMB extends BaseMB {

    private static final Parser<Usuario, UsuarioDTO> PARSER_USUARIO = new ParserUsuarioToUsuarioDTO();
    private UsuarioDTO usuarioDTO;
    private List<UsuarioDTO> usuarioDTOs;
    @EJB
    private UsuarioService service;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao("idParaEdicao");
        if (id != null && id > 0) {
            this.usuarioDTO = PARSER_USUARIO.parse(this.service.pesquisarPorId(id));
            removeAtributoSessao("idParaEdicao");
        } else {
            this.usuarioDTO = new UsuarioDTO();
        }
        this.usuarioDTOs = PARSER_USUARIO.massiveParse(this.service.buscarTodos());
    }

    public void salvar() {
        if (this.usuarioDTO.getId() == 0) {
            usuarioDTO.setId(null);
            this.service.salvar(PARSER_USUARIO.inverseParse(this.usuarioDTO));
            inserirMensagemInformativa("Usuario inserido com sucesso!!!");
        } else {
            this.service.editar(PARSER_USUARIO.inverseParse(this.usuarioDTO));
            inserirMensagemInformativa("Usuario editado com sucesso!!!");
        }
    }

    public void deletar() {
        this.service.deletar(PARSER_USUARIO.inverseParse(this.usuarioDTO));
        this.usuarioDTOs = PARSER_USUARIO.massiveParse(this.service.buscarTodos());
        inserirMensagemInformativa("Usuario removido com sucesso!!!");
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public List<UsuarioDTO> getUsuarioDTOs() {
        return usuarioDTOs;
    }

    public void setUsuarioDTOs(List<UsuarioDTO> usuarioDTOs) {
        this.usuarioDTOs = usuarioDTOs;
    }

}
