package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Paciente;
/**
 * DTO de resposta para a entidade Paciente.
 * Contém apenas dados que serão expostos ao cliente.
 */
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

    /**
     * Converte um objeto Paciente em PacienteResponseDTO.
     *
     * @param paciente Entidade Paciente
     * @return DTO correspondente
     */
    public PacienteResponseDTO convertToPacienteDto(Paciente paciente){
        if(paciente == null) return null;
        return new PacienteResponseDTO(paciente.getNome(), paciente.getCpf(),paciente.getVulnerabilidade(), paciente.getAptidao());
    }


    /**
     * Converte este DTO em objeto Usuario.
     *
     * @param dto DTO de paciente
     * @return Objeto Paciente correspondente
     */
    public Paciente convertDtoToPaciente(PacienteResponseDTO dto){
        return new Paciente(dto.nome, dto.cpf, dto.vulnerabilidade, dto.aptidao);
    }
}

