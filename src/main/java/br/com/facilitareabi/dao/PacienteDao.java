package br.com.facilitareabi.dao;

import br.com.facilitareabi.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe de acesso a dados (DAO) responsável por operações CRUD para a entidade Paciente.
 * Cada método interage com a tabela "paciente" do banco de dados usando JDBC.
 */
public class PacienteDao {


    private  Connection conn;
    public PacienteDao() {
        this.conn = ConnectionFactory.obterConexao();
    }

    /**
     * Cadastra um novo paciente no banco de dados.
     *
     * @param paciente Objeto Paciente a ser cadastrado
     * @throws SQLException Caso ocorra algum erro de acesso ao banco
     */
    public void cadastrarPaciente(Paciente paciente) throws SQLException{
        String sql = "INSERT INTO paciente (id_paciente, nome, dataNascimento, email, telefone, cpf, vulnerabilidade, aptidao) VALUES (paciente_seq.NEXTVAL,?, ?,?, ?, ?, ?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
        }
    }

    /**
     * Busca um paciente pelo nome.
     *
     * @param nome Nome do paciente
     * @return Objeto Paciente encontrado ou null se não existir
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public Paciente buscarPorNome(String nome)throws SQLException {
        String sql = "SELECT * FROM paciente WHERE nome = ?";
        Paciente paciente = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
        }
        return paciente;
    }

    /**
     * Atualiza os dados de um paciente existente.
     *
     * @param paciente Objeto Paciente com ID existente
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void atualizarPaciente(Paciente paciente) throws SQLException{
        String sql = "UPDATE paciente SET nome = ?, dataNascimento = ?, email = ?, telefone = ?, cpf = ?, vulnerabilidade = ?, aptidao= ? WHERE id_paciente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getNome());
            ps.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(3, paciente.getEmail());
            ps.setString(4, paciente.getTelefone());
            ps.setString(5, paciente.getCpf());
            ps.setString(6, paciente.getVulnerabilidade());
            ps.setString(7, paciente.getAptidao());
            ps.setInt(8, paciente.getId_paciente());
            ps.executeUpdate();
        }
    }

    /**
     * Exclui um paciente pelo nome.
     *
     * @param nome Nome do paciente a ser removido
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void excluirPaciente(String nome)throws SQLException {
        String sql = "DELETE FROM paciente WHERE nome = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os pacientes cadastrados no banco.
     *
     * @return Lista de objetos Paciente
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public List<Paciente> listarPacientes()throws SQLException {
        String sql = "SELECT * FROM paciente";
        List<Paciente> lista = new ArrayList<>();
        try (
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
        }
        return lista;
    }
}