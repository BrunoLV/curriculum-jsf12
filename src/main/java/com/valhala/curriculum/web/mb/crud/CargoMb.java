/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.CargoDto;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.mappers.CargoMapper;
import com.valhala.curriculum.web.mb.BaseMb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

/**
 * @author bruno
 */
public class CargoMb extends BaseMb {

    private static final long serialVersionUID = 4240025875447639696L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    @EJB
    private CargoService cargoService;
    private CargoDto cargo;
    private List<CargoDto> listaCargo;

    private CargoMapper cargoMapper = CargoMapper.INSTANCE;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.cargo = cargoMapper.cargoToCargoDto(this.cargoService.pesquisarPorId(id));
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.cargo = new CargoDto();
        }
        this.listaCargo = cargoMapper.listaCargoToListaCargoDto(this.cargoService.buscarTodos());
    } // fim do metodo init

    public void salvar() {
        if (cargo.getId() == 0) {
            cargo.setId(null);
            this.cargoService.salvar(cargoMapper.cargoDtoToCargo(cargo));
            inserirMensagemInformativa("Cargo inserido com sucesso!!!");
        } else {
            this.cargoService.editar(cargoMapper.cargoDtoToCargo(cargo));
            inserirMensagemInformativa("Cargo editado com sucesso!!!");
        } // fim do bloco if/else
    } // fim do metodo salvar

    public void deletar() {
        this.cargoService.deletar(cargoMapper.cargoDtoToCargo(cargo));
        this.listaCargo = cargoMapper.listaCargoToListaCargoDto(this.cargoService.buscarTodos());
        inserirMensagemInformativa("Cargo removido com sucesso!!!");
    } // fim do metodo deletar

    public CargoDto getCargo() {
        return cargo;
    }

    public void setCargo(CargoDto cargo) {
        this.cargo = cargo;
    }

    public List<CargoDto> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<CargoDto> listaCargo) {
        this.listaCargo = listaCargo;
    }

} // fim da classe CargoMb
