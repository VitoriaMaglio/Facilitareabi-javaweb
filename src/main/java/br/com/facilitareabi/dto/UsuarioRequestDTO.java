package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Usuario;
/**
 * DTO de requisição para a entidade Usuario.
 * Contém dados que o cliente envia para criar ou atualizar um usuário.
 */
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

    /**
     * Converte um objeto Usuario em UsuarioRequestDTO.
     *
     * @param usuario Entidade Usuario
     * @return DTO correspondente
     */
    public UsuarioRequestDTO convertToUsuarioRequestDTO(Usuario usuario){
        return new UsuarioRequestDTO(usuario.getLogin(), usuario.getSenha(), usuario.getFeedback());
    }

    /**
     * Converte este DTO em objeto Usuario.
     *
     * @param dto DTO de usuário
     * @return Objeto Usuario correspondente
     */
    public Usuario convertToUsuario(UsuarioRequestDTO dto){
        return new Usuario( dto.login, dto.senha, dto.feedback);
    }

}

