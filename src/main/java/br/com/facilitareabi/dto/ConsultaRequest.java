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

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public StatusConsultaEnum getStatusConsulta() {
        return StatusConsulta;
    }

    public void setStatusConsulta(StatusConsultaEnum statusConsulta) {
        StatusConsulta = statusConsulta;
    }

    public String getMotivoFalta() {
        return motivoFalta;
    }

    public void setMotivoFalta(String motivoFalta) {
        this.motivoFalta = motivoFalta;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
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
