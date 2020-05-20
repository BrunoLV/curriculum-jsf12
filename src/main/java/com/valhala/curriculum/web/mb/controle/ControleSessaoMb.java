package com.valhala.curriculum.web.mb.controle;

import javax.ejb.EJB;

import org.modelmapper.ModelMapper;

import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.Usuario;
import com.valhala.curriculum.model.enumerados.Roles;
import com.valhala.curriculum.web.mb.BaseMb;

public class ControleSessaoMb extends BaseMb {

    private static final long serialVersionUID = -5989290915191663301L;
    
    @EJB
    private UsuarioService service;
        
    private ModelMapper modelMapper = new ModelMapper();

	public String getPrincipalName() {
        return getRequest().getUserPrincipal().getName();
    }

    public boolean isUserInRoleAdmin() {
        return getRequest().isUserInRole(Roles.ADMIN.getNome());
    }

    public boolean isUserInRoleUser() {
        return getRequest().isUserInRole(Roles.USER.getNome());
    }

    public boolean isUserInRoleUserOrAdmin() {
        return isUserInRoleUser() || isUserInRoleAdmin();
    }
    
    public UsuarioDto obtemUsuarioLogado() {
    	Usuario usuario = service.buscarUsuarioPorEmail(getPrincipalName());
    	return modelMapper.map(usuario, UsuarioDto.class);
    }
    

}
