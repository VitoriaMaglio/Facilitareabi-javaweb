package br.com.facilitareabi.model;

import br.com.facilitareabi.enums.StatusConsultaEnum;

import java.time.LocalDate;

public class Usuario {
    //atributtes
    private int id;
    private String login;
    private String senha;
    private String feedback;
    //Construtor
    public Usuario() {
    }
    public Usuario(int id, String login, String senha, String feedback) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.feedback = feedback;
    }

    public Usuario(String login, String feedback) {
        this.login = login;
        this.feedback = feedback;
    }

    public Usuario(String login, String senha, String feedback) {
        this.login = login;
        this.senha = senha;
        this.feedback = feedback;
    }



    //getters e setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}