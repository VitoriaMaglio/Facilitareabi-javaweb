package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.ConsultaDao;
import br.com.facilitareabi.dto.ConsultaRequest;
import br.com.facilitareabi.dto.ConsultaResponse;
import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ConsultaService {
    boolean verificarAptoParaConsulta(Paciente paciente);
    void remarcarConsulta(Consulta consulta, LocalDate novaData, String motivoFalta);
    void cancelarConsulta(Consulta consulta, String motivoFalta);
    ConsultaDao consultaDao = new ConsultaDao();
    ConsultaResponse buscarPorData(LocalDate data) throws SQLException;
    void cadastrarConsulta(ConsultaRequest consultaRequest);

    List<ConsultaResponse> listarConsulta()  throws SQLException;;
}
