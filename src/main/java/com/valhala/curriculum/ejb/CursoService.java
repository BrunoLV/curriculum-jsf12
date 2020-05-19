package com.valhala.curriculum.ejb;

import javax.ejb.Local;

import com.valhala.curriculum.model.Curso;

@Local
public interface CursoService extends BaseService<Curso> {
}
