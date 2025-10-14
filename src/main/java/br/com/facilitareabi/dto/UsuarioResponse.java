package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Usuario;

public class UsuarioResponse {

    private String login;
    private String feedback;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String login, String feedback) {
        this.login = login;
        this.feedback = feedback;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    //Método conversão de usuário para usuario dto

    public UsuarioResponse convertToUsuarioResponseDto(Usuario usuario){
        return new UsuarioResponse(usuario.getLogin(), usuario.getFeedback());
    }

    //Método conversão de dto para usuario

    public Usuario convertToUsuario(UsuarioResponse dto){
        return new Usuario(dto.login, dto.feedback);
    }
}
