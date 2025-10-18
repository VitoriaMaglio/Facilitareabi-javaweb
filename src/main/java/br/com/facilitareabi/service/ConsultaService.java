package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.ConsultaDao;
import br.com.facilitareabi.dto.ConsultaRequestDTO;
import br.com.facilitareabi.dto.ConsultaResponseDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ConsultaService {
    //boolean verificarAptoParaConsulta(Paciente paciente);
    //void remarcarConsulta(Consulta consulta, LocalDate novaData, String motivoFalta);
    //void cancelarConsulta(Consulta consulta, String motivoFalta);
    ConsultaDao consultaDao = new ConsultaDao();
    ConsultaResponseDTO buscarPorData(LocalDate data) throws SQLException;
    void cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO) throws SQLException;
    List<ConsultaResponseDTO> listarConsulta()  throws SQLException;
    ConsultaResponseDTO atualizarConsulta(int id, ConsultaRequestDTO request) throws SQLException;
    void excluirConsultaData(int id) throws SQLException;
}
