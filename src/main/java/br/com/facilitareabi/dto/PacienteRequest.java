package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;

import java.time.LocalDate;
import java.util.List;

public class PacienteRequest {


    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String vulnerabilidade;
    private String aptidao;

    public PacienteRequest() {
    }

    public PacienteRequest(String nome, String cpf, LocalDate dataNascimento, String telefone, String email, String vulnerabilidade, String aptidao) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.vulnerabilidade = vulnerabilidade;
        this.aptidao = aptidao;
    }

    //Método converter paciente para pacientedto
    public PacienteRequest convertToPacienteDto(Paciente paciente){
        return new PacienteRequest(paciente.getNome(), paciente.getCpf(), paciente.getDataNascimento(), paciente.getTelefone(),paciente.getEmail(),paciente.getVulnerabilidade(), paciente.getAptidao());
    }

    //Método converter pacientedto para paciente
    public Paciente convertDtoToPaciente(PacienteRequest dto){
        return new Paciente(dto.nome, dto.cpf,dto.dataNascimento,dto.telefone,dto.email, dto.vulnerabilidade, dto.aptidao);
    }
}
