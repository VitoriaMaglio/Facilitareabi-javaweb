package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.ConsultaDao;
import br.com.facilitareabi.dto.ConsultaRequestDTO;
import br.com.facilitareabi.dto.ConsultaResponseDTO;
import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ConsultaService {
    //boolean verificarAptoParaConsulta(Paciente paciente);
    //void remarcarConsulta(Consulta consulta, LocalDate novaData, String motivoFalta);
    //void cancelarConsulta(Consulta consulta, String motivoFalta);
    ConsultaDao consultaDao = new ConsultaDao();
    ConsultaResponseDTO buscarPorData(LocalDate data) throws SQLException;
    void cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO);
    List<ConsultaResponseDTO> listarConsulta()  throws SQLException;
    ConsultaResponseDTO atualizarConsulta(ConsultaRequestDTO request) throws SQLException;
    void excluirConsultaData(int id) throws SQLException;
}
