package br.com.facilitareabi.service;
import br.com.facilitareabi.dto.PacienteRequestDTO;
import br.com.facilitareabi.dao.PacienteDao;
import br.com.facilitareabi.dto.PacienteResponseDTO;
import br.com.facilitareabi.model.Paciente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe de serviço para a entidade Paciente.
 * Encapsula a lógica de negócio e delega operações ao PacienteDao.
 */
public class PacienteService {

    private  PacienteDao pacienteDao ;
    public PacienteService() {
        this.pacienteDao = new PacienteDao();;
    }

    /**
     * Cadastra um paciente.
     *
     * @param pacienteRequestDTO DTO com os dados do paciente
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void cadastrarPaciente(PacienteRequestDTO pacienteRequestDTO) throws SQLException{
        pacienteDao.cadastrarPaciente(pacienteRequestDTO.convertDtoToPaciente(pacienteRequestDTO));
    }

    /**
     * Busca paciente pelo nome.
     *
     * @param nome Nome do paciente
     * @return DTO do paciente encontrado ou null
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public PacienteResponseDTO buscarPorNome (String nome) throws SQLException{
        Paciente paciente = pacienteDao.buscarPorNome(nome);
        if (paciente == null) {
            return null;
        }
        PacienteResponseDTO dto = new PacienteResponseDTO();
        return dto.convertToPacienteDto(pacienteDao.buscarPorNome(nome));
    }

    /**
     * Lista todos os pacientes cadastrados.
     *
     * @return Lista de DTOs de pacientes
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public List<PacienteResponseDTO> listarPacientes()throws SQLException {
        List<Paciente> pacientes = pacienteDao.listarPacientes();
        List<PacienteResponseDTO> resposta = new ArrayList<>();
        for (Paciente p : pacientes){
            PacienteResponseDTO dto = new PacienteResponseDTO().convertToPacienteDto(p);
            resposta.add(dto);
        }
        return resposta;
    }

    /**
     * Atualiza um paciente existente.
     *
     * @param id ID do paciente
     * @param request DTO com os dados atualizados
     * @return DTO do paciente atualizado
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public PacienteResponseDTO atualizarPaciente(int id, PacienteRequestDTO request) throws SQLException{
        Paciente paciente = new Paciente(
                id,
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

    /**
     * Exclui um paciente pelo nome.
     *
     * @param nome Nome do paciente
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    public void excluirPaciente(String nome) throws SQLException{
        pacienteDao.excluirPaciente(nome);
    }

}
