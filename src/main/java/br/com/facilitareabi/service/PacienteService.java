package br.com.facilitareabi.service;
import br.com.facilitareabi.dto.PacienteRequest;
import br.com.facilitareabi.dao.PacienteDao;
import br.com.facilitareabi.dto.PacienteResponse;
import br.com.facilitareabi.model.Paciente;

import java.sql.SQLException;

public class PacienteService {
    private PacienteDao pacienteDao = new PacienteDao();


    public void cadastrarPaciente(PacienteRequest pacienteRequest) throws SQLException{
        pacienteDao.cadastrarPaciente(pacienteRequest.convertDtoToPaciente(pacienteRequest));
    }
    //cadastrar um novo recurso chamando o banco de dados e convertendo para dados da entidade

    //buscar paciente no banco de dados e retorna um paciente dto; conectando com dao
    public PacienteResponse buscarPorNome (String nome) throws SQLException{
        PacienteResponse dto = new PacienteResponse();
        return dto.convertToPacienteDto(pacienteDao.buscarPorNome(nome));
    }

    public boolean validarPaciente(Paciente paciente) {
        return paciente.getNome() != null && !paciente.getNome().isEmpty()
                && paciente.getCpf() != null && !paciente.getCpf().isEmpty();
    }
}
