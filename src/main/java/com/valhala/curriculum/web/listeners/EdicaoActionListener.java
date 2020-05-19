package com.valhala.curriculum.web.listeners;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpServletRequest;

public class EdicaoActionListener implements ActionListener {

    @Override
	public void processAction(ActionEvent ae) throws AbortProcessingException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Integer idEdicao = Integer.parseInt(request.getParameter("idEdicao"));
        request.getSession().setAttribute("idParaEdicao", idEdicao);
    }

}
