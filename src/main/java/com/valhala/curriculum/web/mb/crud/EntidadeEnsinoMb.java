/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.EntidadeEnsinoDto;
import com.valhala.curriculum.ejb.EntidadeEnsinoService;
import com.valhala.curriculum.mappers.EntidadeEnsinoMapper;
import com.valhala.curriculum.web.mb.BaseMb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

/**
 * @author bruno
 */
public class EntidadeEnsinoMb extends BaseMb {

    private static final long serialVersionUID = -1623101515692059906L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    @EJB
    private EntidadeEnsinoService entidadeEnsinoService;
    private EntidadeEnsinoDto entidadeEnsino;
    private List<EntidadeEnsinoDto> listaEntidadeEnsino;

    private EntidadeEnsinoMapper entidadeEnsinoMapper = EntidadeEnsinoMapper.INSTANCE;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.entidadeEnsino = entidadeEnsinoMapper.entidadeEnsinoToEntidadeEnsinoDto(this.entidadeEnsinoService.pesquisarPorId(id));
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.entidadeEnsino = new EntidadeEnsinoDto();
        }
        this.listaEntidadeEnsino = entidadeEnsinoMapper.listaEntidadeEnsinoToListaEntidadeEnsinoDto(this.entidadeEnsinoService.buscarTodos());
    } // fim do metodo init

    public void salvar() {
        if (entidadeEnsino.getId() == 0) {
            entidadeEnsino.setId(null);
            this.entidadeEnsinoService.salvar(entidadeEnsinoMapper.entidadeEnsinoDtoToEntidadeEnsino(entidadeEnsino));
            inserirMensagemInformativa("EntidadeEnsino inserido com sucesso!!!");
        } else {
            this.entidadeEnsinoService.editar(entidadeEnsinoMapper.entidadeEnsinoDtoToEntidadeEnsino(entidadeEnsino));
            inserirMensagemInformativa("EntidadeEnsino editado com sucesso!!!");
        } // fim do bloco if/else
    } // fim do metodo salvar

    public void deletar() {
        this.entidadeEnsinoService.deletar(entidadeEnsinoMapper.entidadeEnsinoDtoToEntidadeEnsino(entidadeEnsino));
        this.listaEntidadeEnsino = entidadeEnsinoMapper.listaEntidadeEnsinoToListaEntidadeEnsinoDto(this.entidadeEnsinoService.buscarTodos());
        inserirMensagemInformativa("EntidadeEnsino removido com sucesso!!!");
    } // fim do metodo deletar

    public EntidadeEnsinoDto getEntidadeEnsino() {
        return entidadeEnsino;
    }

    public void setEntidadeEnsino(EntidadeEnsinoDto entidadeEnsino) {
        this.entidadeEnsino = entidadeEnsino;
    }

    public List<EntidadeEnsinoDto> getListaEntidadeEnsino() {
        return listaEntidadeEnsino;
    }

    public void setListaEntidadeEnsino(List<EntidadeEnsinoDto> listaEntidadeEnsino) {
        this.listaEntidadeEnsino = listaEntidadeEnsino;
    }

} // fim da classe EntidadeEnsinoMb
