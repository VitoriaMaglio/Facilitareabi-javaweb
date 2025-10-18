package br.com.facilitareabi.service;

import br.com.facilitareabi.dao.ConsultaDao;
import br.com.facilitareabi.dto.ConsultaRequestDTO;
import br.com.facilitareabi.dto.ConsultaResponseDTO;
import br.com.facilitareabi.model.Consulta;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface ConsultaService.
 * Encapsula a lógica de negócio e delega operações ao ConsultaDao.
 */
public class ConsultaServiceImpl implements ConsultaService {


    private  ConsultaDao consultaDao;
    public ConsultaServiceImpl() {
        this.consultaDao = new ConsultaDao();
    }



    @Override
    public ConsultaResponseDTO buscarPorData(LocalDate data) throws SQLException {
        ConsultaResponseDTO dto = new ConsultaResponseDTO();
        return dto.convertToConsultaResponseDTO(consultaDao.buscarPorData(data));
    }

    @Override
    public void cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO) throws SQLException {
        consultaDao.cadastrarConsulta(consultaRequestDTO.convertDtoToConsulta(consultaRequestDTO));
    }

    @Override
    public List<ConsultaResponseDTO> listarConsulta() throws SQLException {
        List<Consulta> consultas = consultaDao.listarConsultas();
        List<ConsultaResponseDTO> resposta = new ArrayList<>();
        for (Consulta c : consultas) {
            ConsultaResponseDTO dto = new ConsultaResponseDTO().convertToConsultaResponseDTO(c);
            resposta.add(dto);
        }
        return resposta;
    }

    @Override
    public ConsultaResponseDTO atualizarConsulta(int id, ConsultaRequestDTO request) throws SQLException {
        Consulta consulta = new Consulta(id, request.getDataConsulta(),request.getStatusConsulta(), request.getEspecializacao(),request.getMotivoFalta(),request.getPaciente());
        consultaDao.atualizarConsulta(consulta);
        return null;
    }

    @Override
    public void excluirConsultaData(int id) throws SQLException {
        consultaDao.excluirConsultaData(id);

    }
}

