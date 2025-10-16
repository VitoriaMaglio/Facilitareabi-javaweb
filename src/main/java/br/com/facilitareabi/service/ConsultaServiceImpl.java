package br.com.facilitareabi.service;

import br.com.facilitareabi.dto.ConsultaRequestDTO;
import br.com.facilitareabi.dto.ConsultaResponseDTO;
import br.com.facilitareabi.enums.StatusConsultaEnum;
import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaServiceImpl implements ConsultaService {
    //@Override
    //public boolean verificarAptoParaConsulta(Paciente paciente) {
    //    if (paciente.getVulnerabilidade() != null
   //             && paciente.getVulnerabilidade().equalsIgnoreCase("Não")) {
   //         return false;
   //     }
   //     return true;
   // }
//    @Override
//    public void remarcarConsulta(Consulta consulta, LocalDate novaData, String motivoFalta) {
//        consulta.setDataConsulta(novaData);
//        consulta.setMotivoFalta(motivoFalta);
//        consulta.setStatusConsulta(StatusConsultaEnum.REMARCADA);
//    }
//    @Override
//    public void cancelarConsulta(Consulta consulta, String motivoFalta) {
//        consulta.setMotivoFalta(motivoFalta);
//        consulta.setStatusConsulta(StatusConsultaEnum.CANCELADA);
//    }

    @Override
    public ConsultaResponseDTO buscarPorData(LocalDate data) throws SQLException {
        ConsultaResponseDTO dto = new ConsultaResponseDTO();
        return dto.convertToConsultaResponseDTO(consultaDao.buscarPorData(data));
    }

    @Override
    public void cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO) {
        consultaDao.cadastrarConsulta(consultaRequestDTO.convertDtoToConsulta(consultaRequestDTO));
    }

    @Override
    public List<ConsultaResponseDTO> listarConsulta() {
        List<Consulta> consultas = consultaDao.listarConsultas();
        List<ConsultaResponseDTO> resposta = new ArrayList<>();
        for (Consulta c : consultas) {
            ConsultaResponseDTO dto = new ConsultaResponseDTO().convertToConsultaResponseDTO(c);
            resposta.add(dto);
        }
        return resposta;
    }

    @Override
    public ConsultaResponseDTO atualizarConsulta(ConsultaRequestDTO request) throws SQLException {
        Consulta consulta = new Consulta(request.getDataConsulta(),request.getStatusConsulta(), request.getEspecializacao(),request.getMotivoFalta(),request.getPaciente());
        consultaDao.atualizarConsulta(consulta);
        return null;
    }

    @Override
    public void excluirConsultaData(int id) throws SQLException {
        consultaDao.excluirConsultaData(id);

    }

//    @Override
//    public void cadastrarConsulta(Consulta consulta) {
//        consultaDao.cadastrarConsulta(consulta);
//        System.out.println("Consulta cadastrada com sucesso!");
//    }
}

