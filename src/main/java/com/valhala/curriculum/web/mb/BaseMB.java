/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.mb;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @author bruno
 */
public abstract class BaseMB implements Serializable {

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    protected HttpSession getSession() {
        HttpSession httpSession = (HttpSession) getExternalContext().getSession(false);
        return httpSession;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected Object getAtributoSessao(String keySessao) {
        return getSession().getAttribute(keySessao);
    }

    protected void setAtributoSessao(String keySessao, Object objeto) {
        getSession().setAttribute(keySessao, objeto);
    }

    protected void removeAtributoSessao(String keySessao) {
        getSession().removeAttribute(keySessao);
    }

    protected void inserirMensagemInformativa(String mensagem) {
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", mensagem));
    }

    protected void inserirMensagemDeErro(String mensagem) {
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem));
    }

}
