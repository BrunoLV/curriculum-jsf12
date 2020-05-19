package com.valhala.curriculum.web.mb.controle;

import com.valhala.curriculum.model.enumerados.Roles;
import com.valhala.curriculum.web.mb.BaseMb;

public class ControleSessaoMb extends BaseMb {

    private static final long serialVersionUID = -5989290915191663301L;

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

}
