/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bruno
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 323287506447181759L;
	
    @Id
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
