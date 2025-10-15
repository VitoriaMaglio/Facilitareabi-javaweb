package br.com.facilitareabi.service;

import br.com.facilitareabi.dto.ConsultaRequest;
import br.com.facilitareabi.dto.ConsultaResponse;
import br.com.facilitareabi.enums.StatusConsultaEnum;
import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;

import java.sql.SQLException;
import java.time.LocalDate;
public class ConsultaServiceImpl implements ConsultaService {
    @Override
    public boolean verificarAptoParaConsulta(Paciente paciente) {
        if (paciente.getVulnerabilidade() != null
                && paciente.getVulnerabilidade().equalsIgnoreCase("Não")) {
            return false;
        }
        return true;
    }
    @Override
    public void remarcarConsulta(Consulta consulta, LocalDate novaData, String motivoFalta) {
        consulta.setDataConsulta(novaData);
        consulta.setMotivoFalta(motivoFalta);
        consulta.setStatusConsulta(StatusConsultaEnum.REMARCADA);
    }
    @Override
    public void cancelarConsulta(Consulta consulta, String motivoFalta) {
        consulta.setMotivoFalta(motivoFalta);
        consulta.setStatusConsulta(StatusConsultaEnum.CANCELADA);
    }

    @Override
    public ConsultaResponse buscarPorData(LocalDate data) throws SQLException {
        ConsultaResponse dto = new ConsultaResponse();
        return dto.convertToConsultaResponseDTO(consultaDao.buscarPorData(data));
    }

    @Override
    public void cadastrarConsulta(ConsultaRequest consultaRequest) {
        consultaDao.cadastrarConsulta(consultaRequest.convertDtoToConsulta(consultaRequest));
    }

//    @Override
//    public void cadastrarConsulta(Consulta consulta) {
//        consultaDao.cadastrarConsulta(consulta);
//        System.out.println("Consulta cadastrada com sucesso!");
//    }
}

