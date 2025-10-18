package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Usuario;
/**
 * DTO de resposta para a entidade Usuario.
 * Contém apenas dados que serão expostos ao cliente.
 */
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

    /**
     * Converte um objeto Usuario em UsuarioResponseDTO.
     *
     * @param usuario Entidade Usuario
     * @return DTO correspondente
     */
    public UsuarioResponseDTO convertToUsuarioResponseDto(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getLogin(), usuario.getFeedback());
    }

    /**
     * Converte este DTO em objeto Usuario.
     *
     * @param dto DTO de usuário
     * @return Objeto Usuario correspondente
     */
    public Usuario convertToUsuario(UsuarioResponseDTO dto){
        return new Usuario(dto.login, dto.feedback);
    }
}
