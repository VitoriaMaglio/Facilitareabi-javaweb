package br.com.facilitareabi.service;
import br.com.facilitareabi.dto.PacienteRequest;
import br.com.facilitareabi.dao.PacienteDao;
import br.com.facilitareabi.dto.PacienteResponse;
import br.com.facilitareabi.model.Paciente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private PacienteDao pacienteDao = new PacienteDao();


    public void cadastrarPaciente(PacienteRequest pacienteRequest) throws SQLException{
        pacienteDao.cadastrarPaciente(pacienteRequest.convertDtoToPaciente(pacienteRequest));
    }
    //cadastrar um novo recurso chamando o banco de dados e convertendo para dados da entidade

    //buscar paciente no banco de dados e retorna um paciente dto; conectando com dao
    public PacienteResponse buscarPorNome (String nome) throws SQLException{
        Paciente paciente = pacienteDao.buscarPorNome(nome);

        if (paciente == null) {
            return null; // retorna null, e o Resource decide o que fazer
        }
        PacienteResponse dto = new PacienteResponse();
        return dto.convertToPacienteDto(pacienteDao.buscarPorNome(nome));
    }

    public boolean validarPaciente(Paciente paciente) {
        return paciente.getNome() != null && !paciente.getNome().isEmpty()
                && paciente.getCpf() != null && !paciente.getCpf().isEmpty();
    }

    public List<PacienteResponse> listarPacientes()throws SQLException {
        List<Paciente> pacientes = pacienteDao.listarPacientes();
        List<PacienteResponse> resposta = new ArrayList<>();
        for (Paciente p : pacientes){
            PacienteResponse dto = new PacienteResponse().convertToPacienteDto(p);
            resposta.add(dto);
        }
        return resposta;
    }
//    public List<PacienteResponse> listarPacientes() throws SQLException {
//        return pacienteDao.listar()
//                .stream()
//                .map(p -> new PacienteResponse().convertToPacienteDto(p))
//                .collect(Collectors.toList());
//    }

    public PacienteResponse atualizarPaciente(PacienteRequest request) throws SQLException{
        Paciente paciente = new Paciente(
                request.getNome(),
                request.getCpf(),
                request.getDataNascimento(),
                        request.getEmail(),
                        request.getTelefone(),
                        request.getVulnerabilidade(),
                        request.getAptidao());
        pacienteDao.atualizarPaciente(paciente);
        return null;
    }
    public void excluirPaciente(String nome) throws SQLException{
        pacienteDao.excluirPaciente(nome);
    }

}
