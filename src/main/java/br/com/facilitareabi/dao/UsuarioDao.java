package br.com.facilitareabi.dao;

import br.com.facilitareabi.model.Usuario;
import br.com.facilitareabi.security.PasswordHash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe de acesso a dados (DAO) responsável por operações CRUD para a entidade Usuario.
 * Cada método interage com a tabela "usuario" do banco de dados usando JDBC.
 */
public class UsuarioDao {

    private  Connection conn;

    public UsuarioDao() {
        this.conn = ConnectionFactory.obterConexao();
    }

    /**
     * Cadastra um novo usuário no banco de dados.
     *
     * @param usuario Objeto Usuario a ser cadastrado
     * @throws SQLException Caso ocorra algum erro de acesso ao banco
     */
    public void cadastrarUsuario(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario (id, login, senha, feedback) VALUES (usuario_seq.NEXTVAL, ?, ?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3,usuario.getFeedback());
            ps.executeUpdate();
            try (PreparedStatement psId = conn.prepareStatement("SELECT usuario_seq.CURRVAL FROM dual");
                 ResultSet rs = psId.executeQuery()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }
    }

    /**
     * Busca um usuário pelo login.
     *
     * @param login Login do usuário
     * @return Usuario encontrado ou null se não existir
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public Usuario buscarLogin(String login) throws SQLException{
        String sql = "SELECT * FROM USUARIO WHERE LOGIN = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFeedback(rs.getString("feedback"));
                return usuario;
            }
        }
        return null;
    }

    /**
     * Lista todos os usuários cadastrados.
     *
     * @return Lista de objetos Usuario
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setFeedback(rs.getString("Feedback"));
                usuarios.add(u);
            }
            return usuarios;
        }
    }

    /**
     * Atualiza o feedback de um usuário específico.
     *
     * @param idUsuario ID do usuário
     * @param feedback Novo feedback
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void atualizarFeedback(int idUsuario, String feedback)throws SQLException {
        String sql = "UPDATE usuario SET feedback = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, feedback);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();

        }
    }

    /**
     * Altera dados de login e senha de um usuário existente.
     *
     * @param usuario Objeto Usuario com ID existente
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void alterarUsuario(Usuario usuario)throws SQLException{
        String sql = "UPDATE USUARIO SET LOGIN = ?, SENHA = ? WHERE ID=?";
        try(PreparedStatement comandoSQL = conn.prepareStatement(sql)){
            comandoSQL.setString(1,usuario.getLogin());
            comandoSQL.setString(2,usuario.getSenha());
            comandoSQL.setInt(3, usuario.getId());
            comandoSQL.executeUpdate();
        }
    }

    /**
     * Exclui um usuário pelo login.
     *
     * @param login Login do usuário a ser removido
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void excluirUsua(String login)throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE LOGIN=?";
        try (PreparedStatement comandoSQL = conn.prepareStatement((sql))) {
            comandoSQL.setString(1, login);
            int linhasAfetadas = comandoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário com login '" + login + "' removido com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com esse login.");
            }
        }
    }

    /**
     * Autentica um usuário comparando a senha informada com a senha criptografada no banco.
     *
     * @param usuario Objeto Usuario com login e senha informados
     * @return Mensagem de sucesso ou erro
     */
    public String autenticarUsuario(Usuario usuario) {
        String sql = "SELECT * FROM tb_usuario WHERE login = ? ";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getLogin());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String senhaCriptografada = rs.getString("senha");
                if(PasswordHash.verificarSenha(usuario.getSenha(), senhaCriptografada))
                    return "Usuário logado com sucesso";
                else
                    return "Usuário e/ou senha inválidos";
            }else
                return "Usuário não encontrado.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


