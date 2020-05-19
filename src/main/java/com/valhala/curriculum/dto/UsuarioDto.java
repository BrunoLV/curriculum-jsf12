package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.valhala.curriculum.dto.enumerados.RolesDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
    private String nome;
    private String email;
    private String senha;
    
    private List<RolesDto> papeis = new ArrayList<RolesDto>();

}
