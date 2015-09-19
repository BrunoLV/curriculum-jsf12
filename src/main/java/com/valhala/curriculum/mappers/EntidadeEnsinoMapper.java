package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.EntidadeEnsinoDto;
import com.valhala.curriculum.model.EntidadeEnsino;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by bruno on 17/09/15.
 */
@Mapper
public interface EntidadeEnsinoMapper {

    EntidadeEnsinoMapper INSTANCE = Mappers.getMapper(EntidadeEnsinoMapper.class);

    EntidadeEnsino entidadeEnsinoDtoToEntidadeEnsino(EntidadeEnsinoDto entidadeEnsinoDto);

    EntidadeEnsinoDto entidadeEnsinoToEntidadeEnsinoDto(EntidadeEnsino entidadeEnsino);

    List<EntidadeEnsino> listaEntidadeEnsinoDtoToListaEntidadeEnsino(List<EntidadeEnsinoDto> listaEntidadeEnsinoDto);

    List<EntidadeEnsinoDto> listaEntidadeEnsinoToListaEntidadeEnsinoDto(List<EntidadeEnsino> listaEntidadeEnsino);

}
