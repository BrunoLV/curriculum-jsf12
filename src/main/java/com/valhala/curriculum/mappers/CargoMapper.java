package com.valhala.curriculum.mappers;

import com.valhala.curriculum.dto.CargoDto;
import com.valhala.curriculum.model.Cargo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by bruno on 14/09/15.
 */
@Mapper
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    Cargo cargoDtoToCargo(CargoDto cargoDto);

    CargoDto cargoToCargoDto(Cargo cargo);

    List<Cargo> listaCargoDtoToListaCargo(List<CargoDto> listaCargoDto);

    List<CargoDto> listaCargoToListaCargoDto(List<Cargo> listaCargo);

}
