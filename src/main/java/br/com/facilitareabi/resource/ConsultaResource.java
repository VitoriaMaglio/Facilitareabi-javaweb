package br.com.facilitareabi.resource;


import br.com.facilitareabi.dto.ConsultaRequestDTO;
import br.com.facilitareabi.dto.ConsultaResponseDTO;

import br.com.facilitareabi.service.ConsultaService;
import br.com.facilitareabi.service.ConsultaServiceImpl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.sql.SQLException;
import java.util.List;


/**
 * Classe representando um recurso REST para gerenciamento de consultas.
 *  Esta classe expõe endpoints HTTP para cadastro, listagem,
 *  atualização e exclusão de consultas médicas.
 *  @see ConsultaService
 *  @see ConsultaServiceImpl
 */

@Path("/consultas")
public class ConsultaResource {
    private ConsultaService consultaService = new ConsultaServiceImpl();
    /**
     * Cadastra uma nova consulta médica.
     * @param consultaRequestDTO DTO contendo dados da consulta
     * @return Response HTTP:
     *         - 201 CREATED se a consulta for cadastrada
     *         - 400 BAD_REQUEST se houver inconsistência nos dados
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO){
        try{
            consultaService.cadastrarConsulta(consultaRequestDTO);
            ConsultaResponseDTO consultaCadastrada = consultaService.buscarPorData(consultaRequestDTO.getDataConsulta());
            if(consultaCadastrada != null){
                return Response.status(Response.Status.CREATED).build();
            } else{
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    /**
     * Lista todas as consultas cadastradas.
     * @return Response HTTP:
     *         - 200 OK com a lista de consultas
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */

    @GET
    @Path("/{cadastradas}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarConsultas() {
        try {
            List<ConsultaResponseDTO> consultaResponsDTOS = consultaService.listarConsulta();
            return Response.ok(consultaResponsDTOS).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao listar consultas: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Atualiza os dados de uma consulta existente.
     * @param id ID da consulta
     * @param request DTO contendo novos dados da consulta
     * @return Response HTTP:
     *         - 200 OK com a consulta atualizada
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */

    @PUT
    @Path("/{id}")
    public Response atualizarConsulta(@PathParam("id") int id, ConsultaRequestDTO request) {
        try {
            ConsultaResponseDTO atualizado = consultaService.atualizarConsulta(id, request);
            return Response.ok(atualizado).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar consulta: " + e.getMessage()).build();
        }
    }

    /**
     * Exclui uma consulta do sistema.
     *
     * @param id ID da consulta a ser excluída
     * @return Response HTTP:
     *         - 200 OK se exclusão for bem-sucedida
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @DELETE
    @Path("/{id}")
    public Response excluirConsulta(@PathParam("id")int id) {
        try {
            consultaService.excluirConsultaData(id);
            return Response.ok().entity("Consulta excluída com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir consulta: " + e.getMessage()).build();
        }
    }
}