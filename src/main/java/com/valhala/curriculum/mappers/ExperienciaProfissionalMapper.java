package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.ExperienciaProfissionalDto;
import com.valhala.curriculum.model.ExperienciaProfissional;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by bruno on 15/09/15.
 */
@Mapper(uses = {CargoMapper.class, EmpresaMapper.class, CurriculoComRelacionamentoMapper.class})
public interface ExperienciaProfissionalMapper {

    ExperienciaProfissionalMapper INSTANCE = Mappers.getMapper(ExperienciaProfissionalMapper.class);

    @Mapping(target = "curriculo", ignore = true)
    ExperienciaProfissional experienciaProfissionalDtoToExperienciaProfissional(ExperienciaProfissionalDto experienciaProfissionalDto);

    @InheritInverseConfiguration
    ExperienciaProfissionalDto experienciaProfissionalToExperienciaProfissionalDto(ExperienciaProfissional experienciaProfissional);

    List<ExperienciaProfissional> listaExperienciaProfissionalDtoToListaExperienciaProfissional(List<ExperienciaProfissionalDto> listaExperienciaProfissionalDto);

    List<ExperienciaProfissionalDto> listaExperienciaProfissionalToExperienciaProfissionalDto(List<ExperienciaProfissional> listaExperienciaProfissional);

}
