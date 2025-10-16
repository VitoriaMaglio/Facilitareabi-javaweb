package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Paciente;

import java.time.LocalDate;

public class PacienteResponse {


    private String nome;
    private String cpf;
    private String vulnerabilidade;
    private String aptidao;

    public PacienteResponse() {
    }

    public PacienteResponse(String nome, String cpf, String vulnerabilidade, String aptidao) {
        this.nome = nome;
        this.cpf = cpf;
        this.vulnerabilidade = vulnerabilidade;
        this.aptidao = aptidao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getVulnerabilidade() {
        return vulnerabilidade;
    }

    public void setVulnerabilidade(String vulnerabilidade) {
        this.vulnerabilidade = vulnerabilidade;
    }

    public String getAptidao() {
        return aptidao;
    }

    public void setAptidao(String aptidao) {
        this.aptidao = aptidao;
    }

    //Método converter paciente para pacientedto
    public PacienteResponse convertToPacienteDto(Paciente paciente){
        if(paciente == null) return null;
        return new PacienteResponse(paciente.getNome(), paciente.getCpf(),paciente.getVulnerabilidade(), paciente.getAptidao());
    }

    //Método converter pacientedto para paciente
    public Paciente convertDtoToPaciente(PacienteResponse dto){
        return new Paciente(dto.nome, dto.cpf, dto.vulnerabilidade, dto.aptidao);
    }
}

