package com.valhala.curriculum.web.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseMb implements Serializable {

	private static final long serialVersionUID = -3230696492429374223L;

	protected Object getAtributoSessao(String keySessao) {
		return getSession().getAttribute(keySessao);
	}

	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}

	protected HttpSession getSession() {
		HttpSession httpSession = (HttpSession) getExternalContext().getSession(false);
		return httpSession;
	}

	protected void inserirMensagemDeErro(String mensagem) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem));
	}

	protected void inserirMensagemInformativa(String mensagem) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", mensagem));
	}

	protected void removeAtributoSessao(String keySessao) {
		getSession().removeAttribute(keySessao);
	}

	protected void setAtributoSessao(String keySessao, Object objeto) {
		getSession().setAttribute(keySessao, objeto);
	}

}
