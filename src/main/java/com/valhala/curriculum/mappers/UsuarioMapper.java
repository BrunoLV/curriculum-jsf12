package com.valhala.curriculum.mappers;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.valhala.curriculum.dto.RolesDto;
import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.model.Roles;
import com.valhala.curriculum.model.Usuario;

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
    
    Roles toRoles(RolesDto rolesDto);
    
    RolesDto toRolesDto(Roles roles);
    
    List<Roles> toRolesList(List<RolesDto> rolesDtoList);
    
    List<RolesDto> toRolesDtoList(List<Roles> rolesList);

}
