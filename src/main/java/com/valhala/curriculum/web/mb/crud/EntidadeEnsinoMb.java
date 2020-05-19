package com.valhala.curriculum.web.mb.crud;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.valhala.curriculum.dto.EntidadeEnsinoDto;
import com.valhala.curriculum.ejb.EntidadeEnsinoService;
import com.valhala.curriculum.model.EntidadeEnsino;
import com.valhala.curriculum.web.mb.BaseMb;

import lombok.Getter;
import lombok.Setter;

public class EntidadeEnsinoMb extends BaseMb {

    private static final long serialVersionUID = -1623101515692059906L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    @EJB
    private EntidadeEnsinoService entidadeEnsinoService;
    
    @Getter
    @Setter
    private EntidadeEnsinoDto entidadeEnsino;

    @Getter
    @Setter
    private List<EntidadeEnsinoDto> listaEntidadeEnsino;

    ModelMapper modelMapper = new ModelMapper();

    public void deletar() {
        this.entidadeEnsinoService.deletar(modelMapper.map(entidadeEnsino, EntidadeEnsino.class));
        carregaListaEntidadesEnsino();
        inserirMensagemInformativa("EntidadeEnsino removido com sucesso!!!");
    }

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.entidadeEnsino = modelMapper.map(this.entidadeEnsinoService.pesquisarPorId(id), EntidadeEnsinoDto.class);
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.entidadeEnsino = new EntidadeEnsinoDto();
        }
        carregaListaEntidadesEnsino();
    }

	private void carregaListaEntidadesEnsino() {
		Type type = new TypeToken<List<EntidadeEnsinoDto>>(){}.getType();
        this.listaEntidadeEnsino = modelMapper.map(this.entidadeEnsinoService.buscarTodos(), type);
	}

    public void salvar() {
        EntidadeEnsino entidadeEnsinoPersistencia = modelMapper.map(entidadeEnsino, EntidadeEnsino.class);
		if (entidadeEnsino.getId() == 0) {
            entidadeEnsino.setId(null);
            this.entidadeEnsinoService.salvar(entidadeEnsinoPersistencia);
            inserirMensagemInformativa("EntidadeEnsino inserido com sucesso!!!");
        } else {
            this.entidadeEnsinoService.editar(entidadeEnsinoPersistencia);
            inserirMensagemInformativa("EntidadeEnsino editado com sucesso!!!");
        }
    }

}
