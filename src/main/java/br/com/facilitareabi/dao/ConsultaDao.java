package br.com.facilitareabi.dao;

import br.com.facilitareabi.enums.StatusConsultaEnum;
import br.com.facilitareabi.model.Consulta;
import br.com.facilitareabi.model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao {
    private Connection conn;
    public ConsultaDao() {
        this.conn = ConnectionFactory.obterConexao();
    }


    public void cadastrarConsulta(Consulta consulta) {
        String sql = "INSERT INTO consulta (dataConsulta, statusConsulta, motivoFalta, especializacao, id_paciente) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(consulta.getDataConsulta()));
            ps.setString(2, consulta.getStatusConsulta().name()); // VARCHAR2 no DB
            ps.setString(3, consulta.getMotivoFalta());
            ps.setString(4, consulta.getEspecializacao());

            ps.setInt(5, consulta.getPaciente().getId_paciente()); // NUMBER no DB

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta.setId(rs.getInt(1));
                }
            }

            System.out.println("Consulta cadastrada com ID: " + consulta.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public Consulta buscarPorData(LocalDate data){
        String sql = "SELECT * FROM consulta WHERE dataConsulta=?";
        Consulta consulta = null;
        try (
              PreparedStatement ps = conn.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
        }
    public void atualizarConsulta(Consulta consulta) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void excluirConsultaData(int id) {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma consulta encontrada com a data: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Consulta> listarConsultas() {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return consultas;
    }
}
