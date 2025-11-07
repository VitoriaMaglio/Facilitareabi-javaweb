package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
/**
 * DTO de requisição para a entidade Paciente.
 * Contém dados que o cliente envia para criar ou atualizar um usuário.
 */
public class PacienteRequestDTO {

    private String nome;
    private String cpf;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String vulnerabilidade;
    private String aptidao;

    public PacienteRequestDTO() {
    }

    public PacienteRequestDTO(String nome, String cpf, LocalDate dataNascimento, String telefone, String email, String vulnerabilidade, String aptidao) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
     * Converte um objeto Paciente em PacienteRequestDTO.
     *
     * @param paciente Entidade Paciente
     * @return DTO correspondente
     */
    public PacienteRequestDTO convertToPacienteDto(Paciente paciente){
        return new PacienteRequestDTO(paciente.getNome(), paciente.getCpf(), paciente.getDataNascimento(), paciente.getTelefone(),paciente.getEmail(),paciente.getVulnerabilidade(), paciente.getAptidao());
    }

    /**
     * Converte este DTO em objeto Paciente.
     *
     * @param dto DTO de paciente
     * @return Objeto Paciente correspondente
     */
    public Paciente convertDtoToPaciente(PacienteRequestDTO dto){
        return new Paciente(dto.nome, dto.cpf,dto.dataNascimento,dto.telefone,dto.email, dto.vulnerabilidade, dto.aptidao);
    }
}
