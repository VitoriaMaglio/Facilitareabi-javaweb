package br.com.facilitareabi.dto;

import br.com.facilitareabi.enums.StatusConsultaEnum;
import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;
import br.com.facilitareabi.model.Usuario;

import java.time.LocalDate;

public class ConsultaRequest {


    private LocalDate dataConsulta;
    private StatusConsultaEnum StatusConsulta;
    private String motivoFalta;
    private String especializacao;
    private Paciente paciente;

    public ConsultaRequest() {
    }

    public ConsultaRequest(LocalDate dataConsulta, StatusConsultaEnum statusConsulta, String motivoFalta, String especializacao, Paciente paciente) {
        this.dataConsulta = dataConsulta;
        StatusConsulta = statusConsulta;
        this.motivoFalta = motivoFalta;
        this.especializacao = especializacao;
        this.paciente = paciente;
    }



    //Método conversão de usuário para usuario dto
    public ConsultaRequest convertToConsultaRequestDTO(Consulta consulta){
        return new ConsultaRequest(consulta.getDataConsulta(),consulta.getStatusConsulta(), consulta.getMotivoFalta(), consulta.getEspecializacao(), consulta.getPaciente());
    }

    //Método conversão de dto para usuario
    public Consulta convertDtoToConsulta(ConsultaRequest dto){
        return new Consulta(dto.dataConsulta,dto.StatusConsulta, dto.motivoFalta, dto.especializacao,dto.paciente);
    }

}
