package br.com.facilitareabi.dao;

import br.facilitareabi.com.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
    public void cadastrarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (id_paciente, nome, dataNascimento, email, telefone, cpf, vulnerabilidade, aptidao) VALUES (paciente_seq.NEXTVAL,?, ?,?, ?, ?, ?,?)";
        try (Connection conn = ConnectionFactory.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, paciente.getNome());
            ps.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(3, paciente.getEmail());
            ps.setString(4, paciente.getTelefone());
            ps.setString(5, paciente.getCpf());
            ps.setString(6, paciente.getVulnerabilidade());
            ps.setString(7, paciente.getAptidao());
            ps.executeUpdate();
            try (PreparedStatement psId = conn.prepareStatement("SELECT paciente_seq.CURRVAL FROM dual");
                 ResultSet rs = psId.executeQuery()) {
                if (rs.next()) {
                    paciente.setId_paciente(rs.getInt(1));
                }
            }
            System.out.println("Paciente cadastrado com ID: " + paciente.getId_paciente());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Paciente buscarPorNome(String nome) {
        String sql = "SELECT * FROM paciente WHERE nome = ?";
        Paciente paciente = null;
        try (Connection conn = ConnectionFactory.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    paciente = new Paciente();
                    paciente.setId_paciente(rs.getInt("id_paciente"));
                    paciente.setNome(rs.getString("nome"));
                    paciente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                    paciente.setEmail(rs.getString("email"));
                    paciente.setTelefone(rs.getString("telefone"));
                    paciente.setCpf(rs.getString("cpf"));
                    paciente.setVulnerabilidade(rs.getString("vulnerabilidade"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }
    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, dataNascimento = ?, email = ?, telefone = ?, cpf = ?, vulnerabilidade = ?, aptidao= ? WHERE id_paciente = ?";
        try (Connection conn = ConnectionFactory.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getNome());
            ps.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(3, paciente.getEmail());
            ps.setString(4, paciente.getTelefone());
            ps.setString(5, paciente.getCpf());
            ps.setString(6, paciente.getVulnerabilidade());
            ps.setString(7, paciente.getAptidao());
            ps.setInt(8, paciente.getId_paciente());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void excluirPaciente(String nome) {
        String sql = "DELETE FROM paciente WHERE nome = ?";
        try (Connection conn = ConnectionFactory.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Paciente> listarPacientes() {
        String sql = "SELECT * FROM paciente";
        List<Paciente> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId_paciente(rs.getInt("id_paciente"));
                paciente.setNome(rs.getString("nome"));
                paciente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setVulnerabilidade(rs.getString("vulnerabilidade"));
                paciente.setAptidao(rs.getString("aptidao"));
                lista.add(paciente);
            }
            for (Paciente p : lista) {
                System.out.println(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}