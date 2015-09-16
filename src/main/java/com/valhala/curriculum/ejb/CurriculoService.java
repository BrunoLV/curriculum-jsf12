package com.valhala.curriculum.ejb;

import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.ExperienciaProfissional;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 05/09/15.
 */
@Local
public interface CurriculoService extends BaseService<Curriculo> {

    Curriculo buscarPorIdComRelacionamento(Serializable id);

    void salvar(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionaisInclusao);

    void editar(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionaisInclusao, List<ExperienciaProfissional> experienciasProfissionaisRemocao);

    void adicionarExperienciasAoCurriculo(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionais);

    void removerExperienciasDoCurriculo(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionais);

}
