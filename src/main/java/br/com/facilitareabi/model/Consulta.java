package br.com.facilitareabi.model;

import br.com.facilitareabi.enums.StatusConsultaEnum;

import java.time.LocalDate;

public class Consulta  {
    //atributtes
    private int id;
    private LocalDate dataConsulta;
    private StatusConsultaEnum StatusConsulta;
    private String motivoFalta;
    private String especializacao;
    private Paciente paciente;
    //Construtor
    public Consulta() {
    }
    public Consulta(int id, LocalDate dataConsulta, StatusConsultaEnum statusConsulta, String motivoFalta, String especializacao, Paciente paciente) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        StatusConsulta = statusConsulta;
        this.motivoFalta = motivoFalta;
        this.especializacao = especializacao;
        this.paciente = paciente;
    }

    public Consulta(LocalDate dataConsulta, StatusConsultaEnum statusConsulta, String motivoFalta, String especializacao, Paciente paciente) {
        this.dataConsulta = dataConsulta;
        StatusConsulta = statusConsulta;
        this.motivoFalta = motivoFalta;
        this.especializacao = especializacao;
        this.paciente = paciente;
    }

    public Consulta(LocalDate dataConsulta, StatusConsultaEnum statusConsulta, String especializacao, Paciente paciente) {
        this.dataConsulta = dataConsulta;
        StatusConsulta = statusConsulta;
        this.especializacao = especializacao;
        this.paciente = paciente;
    }

    //Getters e setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDataConsulta() {return dataConsulta;}
    public void setDataConsulta(LocalDate dataConsulta) {this.dataConsulta = dataConsulta;}
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
    public String getEspecializacao() {return especializacao;}
    public void setEspecializacao(String especializacao) {this.especializacao = especializacao;}
    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}
    @Override
    public String toString() {
        return "Consulta{" +
                "ID=" + id +
                ", DataConsulta=" + dataConsulta +
                ", MotivoFalta='" + motivoFalta + '\'' +
                ", Paciente=" + (paciente != null ? paciente.getNome() : "N/A") +
                "Especialização=" + especializacao +
                '}';
    }
}