package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by bruno on 14/09/15.
 */
@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    List<Usuario> listaUsuarioDtoToListaUsuario(List<UsuarioDto> listaUsuarioDto);

    List<UsuarioDto> listaUsuarioToListaUsuarioDto(List<Usuario> listaUsuario);

}
