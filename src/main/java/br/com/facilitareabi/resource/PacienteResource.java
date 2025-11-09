package br.com.facilitareabi.resource;


import br.com.facilitareabi.dto.PacienteRequestDTO;
import br.com.facilitareabi.dto.PacienteResponseDTO;
import br.com.facilitareabi.dao.PacienteDao;
import br.com.facilitareabi.model.Paciente;
import br.com.facilitareabi.service.PacienteService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.sql.SQLException;
import java.util.List;
/**
 * Classe representando um recurso REST para gerenciamento de pacientes.
 * Esta classe expõe endpoints HTTP para cadastro, listagem, busca, atualização e exclusão de pacientes.
 * @see PacienteService
 * @see PacienteDao
 * */
@Path("pacientes")
public class PacienteResource {

    private PacienteService pacienteService = new PacienteService();
    /**
     * Cadastra um novo paciente no sistema.
     *
     * @param request DTO contendo dados do paciente
     * @return Response HTTP:
     * - 201 CREATED se o paciente for cadastrado
     * - 400 BAD_REQUEST se houver inconsistência nos dados
     * - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPaciente(PacienteRequestDTO request) {
        try {
            pacienteService.cadastrarPaciente(request);
            PacienteResponseDTO cadastrado = pacienteService.buscarPorNome(request.getNome());
            if (cadastrado != null) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Lista todos os pacientes cadastrados.
     *
     * @return Response HTTP:
     * - 200 OK com a lista de pacientes
     * - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPacientes() {
        try {
            List<PacienteResponseDTO> pacientes = pacienteService.listarPacientes();
            return Response.ok(pacientes).build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao listar pacientes: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Busca um paciente pelo nome.
     *
     * @param nome Nome do paciente
     * @return Response HTTP:
     * - 200 OK com o paciente encontrado
     * - 404 NOT_FOUND se paciente não existir
     * - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @GET
    @Path("/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPacienteNome(@PathParam("nome") String nome) {
        try {
            PacienteResponseDTO pacienteResponseDTO = pacienteService.buscarPorNome(nome);
            if (pacienteResponseDTO != null) {
                return Response.ok(pacienteResponseDTO).build();
            } else
                return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"Paciente não encontrado\"}").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao buscar paciente: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Atualiza os dados de um paciente existente.
     *
     * @param id      ID do paciente
     * @param request DTO contendo novos dados do paciente
     * @return Response HTTP:
     * - 200 OK se atualização for bem-sucedida
     * - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @PUT
    @Path("/{id_paciente}")
    public Response alterarPaciente(@PathParam("id_paciente") int id, PacienteRequestDTO request) {
        try {
            PacienteResponseDTO atualizado = pacienteService.atualizarPaciente(id, request);
            return Response.ok().entity("Paciente atualizado!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar paciente: " + e.getMessage()).build();
        }
    }

    /**
     * Exclui um paciente do sistema.
     *
     * @param nome Nome do paciente a ser excluído
     * @return Response HTTP:
     * - 200 OK se exclusão for bem-sucedida
     * - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @DELETE
    @Path("/{nome}")
    public Response deletarPaciente(@PathParam("nome") String nome) {
        try {
            pacienteService.excluirPaciente(nome);
            return Response.ok().entity("Paciente excluído!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir paciente: " + e.getMessage()).build();
        }
    }

}