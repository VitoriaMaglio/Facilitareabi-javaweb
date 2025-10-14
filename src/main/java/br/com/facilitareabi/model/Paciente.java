package br.com.facilitareabi.model;
import java.time.LocalDate;
import java.util.List;

public class Paciente {
    //atributtes
    private int id_paciente;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String vulnerabilidade;
    private String aptidao;
    private List<Consulta> consultas;
    //Construtor
    public Paciente() {
    }
    public Paciente(int id_paciente, String nome, String cpf, LocalDate dataNascimento, String telefone, String email, String vulnerabilidade, String aptidao) {
        this.id_paciente = id_paciente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.vulnerabilidade = vulnerabilidade;
        this.aptidao = aptidao;
    }

    public Paciente(String nome, String cpf, LocalDate dataNascimento, String telefone, String email, String vulnerabilidade, String aptidao) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.vulnerabilidade = vulnerabilidade;
        this.aptidao = aptidao;
    }

    public Paciente(String nome, String cpf, String vulnerabilidade, String aptidao) {
        this.nome = nome;
        this.cpf = cpf;
        this.vulnerabilidade = vulnerabilidade;
        this.aptidao = aptidao;
    }

    public Paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    //Getters e setters
    public int getId_paciente() {return id_paciente;}
    public void setId_paciente(int id_paciente) {this.id_paciente = id_paciente;}
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
    public LocalDate getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}
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
    @Override
    public String toString() {
        return "Paciente{" +
                "ID=" + id_paciente +
                ", Nome='" + nome + '\'' +
                ", DataNascimento=" + dataNascimento +
                ", Email='" + email + '\'' +
                ", Telefone='" + telefone + '\'' +
                ", CPF='" + cpf + '\'' +
                ", Vulnerabilidade='" + vulnerabilidade + '\'' +
                ", Aptidão='"+ aptidao + '\'' +
                '}';
    }
}