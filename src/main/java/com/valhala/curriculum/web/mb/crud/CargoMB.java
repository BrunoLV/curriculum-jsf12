/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.CargoDTO;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.model.Cargo;
import com.valhala.curriculum.utils.api.Parser;
import com.valhala.curriculum.utils.impl.ParserCargoToCargoDTO;
import com.valhala.curriculum.web.mb.BaseMB;
import com.valhala.curriculum.web.mb.controle.ControleSessaoMB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

/**
 * @author bruno
 */
public class CargoMB extends BaseMB {

    private static final Parser<Cargo, CargoDTO> PARSER_CARGO = new ParserCargoToCargoDTO();
    @EJB
    private CargoService cargoService;
    private CargoDTO cargoDTO;
    private List<CargoDTO> cargosDTOs;
    private ControleSessaoMB sessaoMB;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao("idParaEdicao");
        if (id != null && id > 0) {
            this.cargoDTO = PARSER_CARGO.parse(this.cargoService.pesquisarPorId(id));
            removeAtributoSessao("idParaEdicao");
        } else {
            this.cargoDTO = new CargoDTO();
        }
        this.cargosDTOs = PARSER_CARGO.massiveParse(this.cargoService.buscarTodos());
    } // fim do metodo init

    public void salvar() {
        if (cargoDTO.getId() == 0) {
            cargoDTO.setId(null);
            this.cargoService.salvar(PARSER_CARGO.inverseParse(cargoDTO));
            inserirMensagemInformativa("Cargo inserido com sucesso!!!");
        } else {
            this.cargoService.editar(PARSER_CARGO.inverseParse(cargoDTO));
            inserirMensagemInformativa("Cargo editado com sucesso!!!");
        } // fim do bloco if/else
    } // fim do metodo salvar

    public void deletar() {
        this.cargoService.deletar(PARSER_CARGO.inverseParse(cargoDTO));
        this.cargosDTOs = PARSER_CARGO.massiveParse(this.cargoService.buscarTodos());
        inserirMensagemInformativa("Cargo removido com sucesso!!!");
    } // fim do metodo deletar

    public CargoDTO getCargoDTO() {
        return cargoDTO;
    }

    public void setCargoDTO(CargoDTO cargoDTO) {
        this.cargoDTO = cargoDTO;
    }

    public List<CargoDTO> getCargosDTOs() {
        return cargosDTOs;
    }

    public void setCargosDTOs(List<CargoDTO> cargosDTOs) {
        this.cargosDTOs = cargosDTOs;
    }

    public ControleSessaoMB getSessaoMB() {
        return sessaoMB;
    }

    public void setSessaoMB(ControleSessaoMB sessaoMB) {
        this.sessaoMB = sessaoMB;
    }

} // fim da classe CargoMB
