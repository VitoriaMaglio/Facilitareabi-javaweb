package br.com.facilitareabi.dao;

import br.com.facilitareabi.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection conn;
    public UsuarioDao() {
        this.conn = ConnectionFactory.obterConexao();
    }


    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (id, login, senha) VALUES (usuario_seq.NEXTVAL, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {



            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.executeUpdate();
            try (PreparedStatement psId = conn.prepareStatement("SELECT usuario_seq.CURRVAL FROM dual");
                 ResultSet rs = psId.executeQuery()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
            System.out.println("Usuário cadastrado com ID: " + usuario.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Usuario buscarLogin(String login) {
        String sql = "SELECT * FROM USUARIO WHERE LOGIN = ?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFeedback(rs.getString("feedback"));

                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setLogin(rs.getString("login"));

            u.setFeedback(rs.getString("Feedback"));

            usuarios.add(u);
        }
        rs.close();
        ps.close();
        return usuarios;
    }

    public void atualizarFeedback(int idUsuario, String feedback) {
        String sql = "UPDATE usuario SET feedback = ? WHERE id = ?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, feedback);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            System.out.println("Feedback atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterarUsuario(Usuario usuario){
        String sql = "UPDATE USUARIO SET LOGIN = ?, SENHA = ? WHERE ID=?";
        try(
            PreparedStatement comandoSQL = conn.prepareStatement(sql)){
            comandoSQL.setString(1,usuario.getLogin());
            comandoSQL.setString(2,usuario.getSenha());
            comandoSQL.setInt(3, usuario.getId());
            comandoSQL.executeUpdate();
            comandoSQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean existeUsuarioPorLogin(String login) {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE LOGIN = ?";
        try (
             PreparedStatement comandoSQL = conn.prepareStatement(sql)) {
            comandoSQL.setString(1, login);
            ResultSet rs = comandoSQL.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Se COUNT > 0, usuário existe
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar usuário: " + e.getMessage(), e);
        }
        return false;
    }
    public void excluirUsua(String login) {
        String sql = "DELETE FROM USUARIO WHERE LOGIN=?";
        try (
             PreparedStatement comandoSQL = conn.prepareStatement((sql))) {
            comandoSQL.setString(1,
                    login);
            int linhasAfetadas = comandoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário com login '" + login + "' removido com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com esse login.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário: " + e.getMessage(), e);
        }
    }

    public String autenticarUsuario(Usuario usuario) {
        return null;
    }

}
