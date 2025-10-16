package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Usuario;

public class UsuarioResponseDTO {

    private String login;
    private String feedback;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(String login, String feedback) {
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

    public UsuarioResponseDTO convertToUsuarioResponseDto(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getLogin(), usuario.getFeedback());
    }

    //Método conversão de dto para usuario

    public Usuario convertToUsuario(UsuarioResponseDTO dto){
        return new Usuario(dto.login, dto.feedback);
    }
}
