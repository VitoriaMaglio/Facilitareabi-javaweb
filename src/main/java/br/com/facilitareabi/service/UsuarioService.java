package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.UsuarioDao;
import br.com.facilitareabi.dto.UsuarioLoginDto;
import br.com.facilitareabi.dto.UsuarioRequestDTO;
import br.com.facilitareabi.dto.UsuarioResponseDTO;
import br.com.facilitareabi.model.Usuario;
import br.com.facilitareabi.security.PasswordHash;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe de serviço para a entidade Usuario.
 * Encapsula a lógica de negócio e delega operações ao UsuarioDao.
 */
public class UsuarioService {

    private UsuarioDao usuarioDao;
    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    /**
     * Autentica um usuário.
     *
     * //@param dto DTO com login e senha
     * @return Mensagem de autenticação
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public String autenticarUsuario(Usuario usuario) throws SQLException {
        return usuarioDao.autenticarUsuario(usuario);
   }

    /**
     * Cadastra um usuário.
     *
     * @param usuarioDto DTO com dados do usuário
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void cadastrarUsuario(UsuarioRequestDTO usuarioDto) throws SQLException {
        if (usuarioDto.getLogin() == null ){
            throw new IllegalArgumentException("Login é obrigatório");
        }
        String senhaCriptografada = PasswordHash.hashPassword(usuarioDto.getSenha());
        usuarioDto.setSenha(senhaCriptografada);
        usuarioDao.cadastrarUsuario(usuarioDto.convertToUsuario(usuarioDto));
    }

    /**
     * Busca usuário pelo login.
     *
     * @param login Login do usuário
     * @return DTO do usuário encontrado
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public UsuarioResponseDTO buscarLogin(String login) throws SQLException {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        return dto.convertToUsuarioResponseDto(usuarioDao.buscarLogin(login));
    }

    /**
     * Lista todos os usuários cadastrados.
     *
     * @return Lista de DTOs de usuários
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public List<UsuarioResponseDTO> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        List<UsuarioResponseDTO> resposta = new ArrayList<>();
        for (Usuario u : usuarios) {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setLogin(u.getLogin());
            dto.setFeedback(u.getFeedback());
            resposta.add(dto);
        }
        return resposta;
    }


    /**
     * Atualiza dados de um usuário existente.
     *
     * @param id ID do usuário
     * @param request DTO com dados atualizados
     * @return DTO do usuário atualizado
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public UsuarioResponseDTO alterarUsuario(int id, UsuarioRequestDTO request) throws SQLException {
        Usuario usuario = new Usuario( id, request.getLogin(), request.getSenha(), request.getFeedback());
        usuarioDao.alterarUsuario(usuario);
        return null;
    }

    /**
     * Exclui usuário pelo login.
     *
     * @param login Login do usuário
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void excluirUsua(String login) throws SQLException {
        usuarioDao.excluirUsua(login);
    }

}
