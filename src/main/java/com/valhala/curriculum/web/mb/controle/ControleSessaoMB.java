package com.valhala.curriculum.web.mb.controle;

import com.valhala.curriculum.model.Roles;
import com.valhala.curriculum.web.mb.BaseMB;

/**
 * Created by bruno on 05/09/15.
 */
public class ControleSessaoMB extends BaseMB {

    public boolean isUserInRoleUser() {
        return getRequest().isUserInRole(Roles.USER.getNome());
    }

    public boolean isUserInRoleAdmin() {
        return getRequest().isUserInRole(Roles.ADMIN.getNome());
    }

    public boolean isUserInRoleUserOrAdmin() {
        return isUserInRoleUser() || isUserInRoleAdmin();
    }

    public String getPrincipalName() {
        return getRequest().getUserPrincipal().getName();
    }

}
