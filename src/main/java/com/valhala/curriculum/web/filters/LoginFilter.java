/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author bruno
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig fc) throws ServletException {
    }

    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        String userName = ((HttpServletRequest) sr).getUserPrincipal().getName();
        System.out.println("Usuario: " + userName);
        fc.doFilter(sr, sr1);
    }

    public void destroy() {
    }

}
