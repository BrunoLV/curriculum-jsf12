package com.valhala.curriculum.ejb.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by bruno on 08/09/15.
 */
public abstract class BaseServiceBean {

    @PersistenceContext(name = "curriculum-PU")
    protected EntityManager manager;

}
