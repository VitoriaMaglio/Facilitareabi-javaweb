package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Usuario;

public class UsuarioRequestDTO {


    private String login;
    private String senha;
    private String feedback;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String login, String senha, String feedback) {

        this.login = login;
        this.senha = senha;
        this.feedback = feedback;
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

    //Método conversão de usuário para usuario dto
    public UsuarioRequestDTO convertToUsuarioRequestDTO(Usuario usuario){
        return new UsuarioRequestDTO(usuario.getLogin(), usuario.getSenha(), usuario.getFeedback());
    }

    //Método conversão de dto para usuario
    public Usuario convertToUsuario(UsuarioRequestDTO dto){
        return new Usuario( dto.login, dto.senha, dto.feedback);
    }

}

