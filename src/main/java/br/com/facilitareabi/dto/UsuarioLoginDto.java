package br.com.facilitareabi.dto;

import br.com.facilitareabi.model.Usuario;
/**
 * DTO de login para a entidade Usuario.
 * Contém apenas login e senha, usado para autenticação.
 */
public class UsuarioLoginDto {

    private String login;
    private String senha;

    public UsuarioLoginDto() {}

    public UsuarioLoginDto(String login, String senha) {
        this.login = login;
        this.senha = senha;
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

    /**
     * Converte este DTO em objeto Usuario.
     *
     * @param dto DTO de login
     * @return Objeto Usuario correspondente
     */
    public Usuario convertToUsuario(UsuarioLoginDto dto) {
        return new Usuario(dto.login, dto.senha);
    }
}
