package com.valhala.curriculum.ejb.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseServiceBean {

    @PersistenceContext(name = "curriculum-PU")
    protected EntityManager manager;

}
