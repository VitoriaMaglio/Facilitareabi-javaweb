package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Paciente;

public class PacienteResponseDTO {


    private String nome;
    private String cpf;
    private String vulnerabilidade;
    private String aptidao;

    public PacienteResponseDTO() {
    }

    public PacienteResponseDTO(String nome, String cpf, String vulnerabilidade, String aptidao) {
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
    public PacienteResponseDTO convertToPacienteDto(Paciente paciente){
        if(paciente == null) return null;
        return new PacienteResponseDTO(paciente.getNome(), paciente.getCpf(),paciente.getVulnerabilidade(), paciente.getAptidao());
    }

    //Método converter pacientedto para paciente
    public Paciente convertDtoToPaciente(PacienteResponseDTO dto){
        return new Paciente(dto.nome, dto.cpf, dto.vulnerabilidade, dto.aptidao);
    }
}

