package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.EmpresaDto;
import com.valhala.curriculum.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by bruno on 14/09/15.
 */
@Mapper
public interface EmpresaMapper {

    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    Empresa empresaDtoToEmpresa(EmpresaDto empresaDto);

    EmpresaDto empresaToEmpresaDto(Empresa empresa);

    List<Empresa> listaEmpresaDtoToListaEmpresa(List<EmpresaDto> listaEmpresaDto);

    List<EmpresaDto> listaEmpresaToListaEmpresaDto(List<Empresa> listaEmpresa);

}
