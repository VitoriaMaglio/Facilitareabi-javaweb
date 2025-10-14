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

    //Método converter paciente para pacientedto
    public PacienteResponse convertToPacienteDto(Paciente paciente){
        return new PacienteResponse(paciente.getNome(), paciente.getCpf(),paciente.getVulnerabilidade(), paciente.getAptidao());
    }

    //Método converter pacientedto para paciente
    public Paciente convertDtoToPaciente(PacienteResponse dto){
        return new Paciente(dto.nome, dto.cpf, dto.vulnerabilidade, dto.aptidao);
    }
}

