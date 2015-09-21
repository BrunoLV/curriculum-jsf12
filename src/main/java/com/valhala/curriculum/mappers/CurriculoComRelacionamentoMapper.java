package com.valhala.curriculum.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.valhala.curriculum.dto.CurriculoDto;
import com.valhala.curriculum.model.Curriculo;

/**
 * Created by bruno on 15/09/15.
 */
@Mapper(uses = {UsuarioMapper.class, ExperienciaProfissionalMapper.class, FormacaoAcademicaMapper.class})
public interface CurriculoComRelacionamentoMapper {

    CurriculoComRelacionamentoMapper INSTANCE = Mappers.getMapper(CurriculoComRelacionamentoMapper.class);
    
    Curriculo curriculoDtoToCurriculo(CurriculoDto curriculoDto);

    CurriculoDto curriculoToCurriculoDto(Curriculo curriculo);

    List<Curriculo> listaCurriculoDtoToListaCurriculo(List<CurriculoDto> listaCurriculoDto);
    List<CurriculoDto> listaCurriculoToListaCurriculoDto(List<Curriculo> listaCurriculo);


}
