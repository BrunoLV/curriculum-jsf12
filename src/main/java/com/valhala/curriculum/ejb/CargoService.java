/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.ejb;

import com.valhala.curriculum.model.Cargo;

import javax.ejb.Local;

/**
 * @author bruno
 */
@Local
public interface CargoService extends BaseService<Cargo> {

}
