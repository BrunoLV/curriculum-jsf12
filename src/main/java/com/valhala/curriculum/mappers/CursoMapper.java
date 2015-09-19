package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.CursoDto;
import com.valhala.curriculum.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by bruno on 17/09/15.
 */
@Mapper
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    Curso cursoDtoToCurso(CursoDto cursoDto);

    CursoDto cursoToCursoDto(Curso curso);

    List<Curso> listaCursoDtoToListaCurso(List<CursoDto> listaCursoDto);

    List<CursoDto> listaCursoToListaCursoDto(List<Curso> listaCurso);

}
