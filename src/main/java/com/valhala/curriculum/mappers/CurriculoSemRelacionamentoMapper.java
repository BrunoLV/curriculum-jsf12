package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.CurriculoDto;
import com.valhala.curriculum.model.Curriculo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by bruno on 15/09/15.
 */
@Mapper(uses = {UsuarioMapper.class, ExperienciaProfissionalMapper.class})
public interface CurriculoSemRelacionamentoMapper {

    CurriculoSemRelacionamentoMapper INSTANCE = Mappers.getMapper(CurriculoSemRelacionamentoMapper.class);


    @Mappings({
            @Mapping(target = "experienciasProfissionais", ignore = true),
            @Mapping(target = "formacoesAcademicas", ignore = true)
    })
    Curriculo curriculoDtoToCurriculo(CurriculoDto curriculoDto);

    @InheritInverseConfiguration
    CurriculoDto curriculoToCurriculoDto(Curriculo curriculo);

    List<Curriculo> listaCurriculoDtoToListaCurriculo(List<CurriculoDto> listaCurriculoDto);

    List<CurriculoDto> listaCurriculoToListaCurriculoDto(List<Curriculo> listaCurriculo);

}
