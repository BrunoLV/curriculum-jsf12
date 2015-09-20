package com.valhala.curriculum.ejb;

import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.ExperienciaProfissional;
import com.valhala.curriculum.model.FormacaoAcademica;

import javax.ejb.Local;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 05/09/15.
 */
@Local
public interface CurriculoService extends BaseService<Curriculo> {

    Curriculo buscarPorIdComRelacionamento(Serializable id);

    void salvar(Curriculo curriculo,
                List<ExperienciaProfissional> experienciasProfissionaisInclusao,
                List<FormacaoAcademica> formacoesAcademicasInclusao);

    void editar(Curriculo curriculo,
                List<ExperienciaProfissional> experienciasProfissionaisInclusao,
                List<FormacaoAcademica> formacoesAcademicasInclusao,
                List<ExperienciaProfissional> experienciasProfissionaisRemocao,
                List<FormacaoAcademica> formacoesAcademicas);

    void editar(Curriculo curriculo, @SuppressWarnings("rawtypes") Map<String, List> mapaDeListaRemocaoRelacionamento);
}
