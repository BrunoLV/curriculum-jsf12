/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.actionlisteners;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author bruno
 */
public class EdicaoActionListener implements ActionListener {

    public void processAction(ActionEvent ae) throws AbortProcessingException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Integer idEdicao = Integer.parseInt(request.getParameter("idEdicao"));
        request.getSession().setAttribute("idParaEdicao", idEdicao);
    }

}
