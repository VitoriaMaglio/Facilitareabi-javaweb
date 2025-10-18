package br.com.facilitareabi.dao;

import br.com.facilitareabi.enums.StatusConsultaEnum;
import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe de acesso a dados (DAO) responsável pelas operações CRUD da entidade Consulta.
 * Cada método interage com a tabela "consulta" no banco de dados usando JDBC.
 */
public class ConsultaDao {


    private Connection conn;
    public ConsultaDao() {
        this.conn = ConnectionFactory.obterConexao();
    }

    /**
     * Cadastra uma nova consulta no banco.
     *
     * @param consulta Objeto Consulta a ser cadastrado
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void cadastrarConsulta(Consulta consulta) throws SQLException{
        String sql = "INSERT INTO consulta (id,dataConsulta, statusConsulta, motivoFalta, especializacao, id_paciente) "
                + "VALUES (consulta_seq.NEXTVAL,?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(consulta.getDataConsulta()));
            ps.setString(2, consulta.getStatusConsulta().name()); // VARCHAR2 no DB
            ps.setString(3, consulta.getMotivoFalta());
            ps.setString(4, consulta.getEspecializacao());
            ps.setInt(5, consulta.getPaciente().getId_paciente()); // NUMBER no DB
            ps.executeUpdate();
            try (PreparedStatement ps2 = conn.prepareStatement("SELECT consulta_seq.CURRVAL FROM dual");
                 ResultSet rs = ps2.executeQuery()) {
                if (rs.next()) {
                    consulta.setId(rs.getInt(1));
                }
            }
        }
    }

    /**
     * Busca uma consulta por data.
     *
     * @param data Data da consulta
     * @return Objeto Consulta encontrado ou null se não existir
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public Consulta buscarPorData(LocalDate data)throws SQLException{
        String sql = "SELECT * FROM consulta WHERE dataConsulta=?";
        Consulta consulta = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (data != null) {
                ps.setDate(1, Date.valueOf(data));
            } else {
                ps.setNull(1, java.sql.Types.DATE);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    consulta = new Consulta();
                    consulta.setId(rs.getInt("id"));
                    consulta.setDataConsulta(rs.getDate("dataConsulta").toLocalDate());
                    consulta.setStatusConsulta(StatusConsultaEnum.valueOf(rs.getString("statusConsulta")));
                    consulta.setMotivoFalta(rs.getString("motivoFalta"));
                    consulta.setEspecializacao(rs.getString("especializacao"));
                    Paciente paciente = new Paciente();
                    paciente.setId_paciente(rs.getInt("id_paciente"));
                    consulta.setPaciente(paciente);
                }
            }
        }
        return consulta;
        }

    /**
     * Atualiza os dados de uma consulta existente.
     *
     * @param consulta Objeto Consulta com ID existente
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void atualizarConsulta(Consulta consulta) throws SQLException{
        String sql = "UPDATE consulta SET dataConsulta = ?, statusConsulta = ?, motivoFalta = ?, especializacao = ?, id_paciente = ? WHERE id = ?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(consulta.getDataConsulta()));
            ps.setString(2, consulta.getStatusConsulta().name());
            ps.setString(3, consulta.getMotivoFalta());
            ps.setString(4,consulta.getEspecializacao());
            ps.setInt(5, consulta.getPaciente().getId_paciente());
            ps.setInt(6, consulta.getId());
            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma consulta encontrada com o ID: " + consulta.getId());
            }
        }
    }

    /**
     * Exclui uma consulta pelo ID.
     *
     * @param id ID da consulta a ser removida
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void excluirConsultaData(int id) throws SQLException{
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma consulta encontrada com a data: " + id);
            }
        }
    }

    /**
     * Lista todas as consultas cadastradas no banco.
     *
     * @return Lista de objetos Consulta
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public List<Consulta> listarConsultas() throws SQLException{
        String sql = "SELECT * FROM consulta";
        List<Consulta> consultas = new ArrayList<>();
        try (
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setDataConsulta(rs.getDate("dataConsulta").toLocalDate());
                consulta.setStatusConsulta(StatusConsultaEnum.valueOf(rs.getString("statusConsulta")));
                consulta.setMotivoFalta(rs.getString("motivoFalta"));
                consulta.setEspecializacao(rs.getString("especializacao"));
                Paciente paciente = new Paciente();
                paciente.setId_paciente(rs.getInt("id_paciente"));
                consulta.setPaciente(paciente);
                consultas.add(consulta);
            }
            for (Consulta c : consultas){
                System.out.println(c);
            }
        } return consultas;
    }
}
