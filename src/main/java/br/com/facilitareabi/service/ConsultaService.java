package br.com.facilitareabi.service;
import br.facilitareabi.com.dao.ConsultaDao;
import br.facilitareabi.com.model.Consulta;
import br.facilitareabi.com.model.Paciente;

import java.time.LocalDate;
public interface ConsultaService {
    boolean verificarAptoParaConsulta(Paciente paciente);
    void remarcarConsulta(Consulta consulta, LocalDate novaData, String motivoFalta);
    void cancelarConsulta(Consulta consulta, String motivoFalta);
    ConsultaDao consultaDao = new ConsultaDao();
    void cadastrarConsulta(Consulta consulta);
}
