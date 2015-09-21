package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.EnumTipoFormacaoDto;
import com.valhala.curriculum.dto.FormacaoAcademicaDto;
import com.valhala.curriculum.model.EnumTipoFormacao;
import com.valhala.curriculum.model.FormacaoAcademica;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * Created by bruno on 17/09/15.
 */
@Mapper(uses = {CursoMapper.class, EntidadeEnsinoMapper.class})
public interface FormacaoAcademicaMapper {

    FormacaoAcademicaMapper INSTANCE = Mappers.getMapper(FormacaoAcademicaMapper.class);

    @Mapping(target = "curriculo", ignore = true)
    FormacaoAcademica formacaoAcademicaDtoToFormacaoAcademica(FormacaoAcademicaDto formacaoAcademicaDto);

    @InheritInverseConfiguration
    FormacaoAcademicaDto formacaoAcademicaToFormacaoAcademicaDto(FormacaoAcademica formacaoAcademica);

    List<FormacaoAcademica> listaFormacaoAcademicaDtoToListaFormacaoAcademica(List<FormacaoAcademicaDto> listaFormacaoAcademicaDto);
    List<FormacaoAcademicaDto> listaFormacaoAcademicaToListaFormacaoAcademicaDto(List<FormacaoAcademica> listaFormacaoAcademica);
    
    Set<FormacaoAcademica> setFormacaoAcademicaDtoToSetFormacaoAcademica(Set<FormacaoAcademicaDto> setFormacaoAcademicaDto);
    Set<FormacaoAcademicaDto> setFormacaoAcademicaToSetFormacaoAcademicaDto(Set<FormacaoAcademica> setFormacaoAcademica);

    EnumTipoFormacao enumTipoFormacaoDtoToEnumTipoFormacao(EnumTipoFormacaoDto enumTipoFormacaoDto);
    EnumTipoFormacaoDto enumTipoFormacaoToEnumTipoFormacaoDto(EnumTipoFormacao enumTipoFormacao);

}
