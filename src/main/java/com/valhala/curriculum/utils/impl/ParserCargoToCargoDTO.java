/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.utils.impl;

import com.valhala.curriculum.dto.CargoDTO;
import com.valhala.curriculum.model.Cargo;
import com.valhala.curriculum.utils.api.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 */
public class ParserCargoToCargoDTO implements Parser<Cargo, CargoDTO> {

    public CargoDTO parse(Cargo cargo) {
        CargoDTO cargoDTO = new CargoDTO();
        cargoDTO.setId(cargo.getId());
        cargoDTO.setNome(cargo.getNome());
        return cargoDTO;
    }

    public Cargo inverseParse(CargoDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setId(dto.getId());
        cargo.setNome(dto.getNome());
        return cargo;
    }

    public List<CargoDTO> massiveParse(List<Cargo> list) {
        List<CargoDTO> dtos = new ArrayList<CargoDTO>();
        for (Cargo cargo : list) {
            dtos.add(parse(cargo));
        }
        return dtos;
    }

    public List<Cargo> massiveInverseParse(List<CargoDTO> list) {
        List<Cargo> cargos = new ArrayList<Cargo>();
        for (CargoDTO dto : list) {
            cargos.add(inverseParse(dto));
        }
        return cargos;
    }

    public CargoDTO parseWithoutRelationship(Cargo objeto) {
        return null;
    }

    public List<CargoDTO> massiveParseWithoutRelationship(List<Cargo> list) {
        return null;
    }

}
