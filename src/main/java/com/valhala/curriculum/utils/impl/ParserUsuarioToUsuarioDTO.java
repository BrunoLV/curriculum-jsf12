/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.utils.impl;

import com.valhala.curriculum.dto.UsuarioDTO;
import com.valhala.curriculum.model.Usuario;
import com.valhala.curriculum.utils.api.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 */
public class ParserUsuarioToUsuarioDTO implements Parser<Usuario, UsuarioDTO> {

    public UsuarioDTO parse(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }

    public Usuario inverseParse(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    public List<UsuarioDTO> massiveParse(List<Usuario> list) {
        List<UsuarioDTO> dtos = new ArrayList<UsuarioDTO>();
        for (Usuario usuario : list) {
            dtos.add(parse(usuario));
        }
        return dtos;
    }

    public List<Usuario> massiveInverseParse(List<UsuarioDTO> list) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        for (UsuarioDTO dto : list) {
            usuarios.add(inverseParse(dto));
        }
        return usuarios;
    }

    public UsuarioDTO parseWithoutRelationship(Usuario objeto) {
        return null;
    }

    public List<UsuarioDTO> massiveParseWithoutRelationship(List<Usuario> list) {
        return null;
    }

}
