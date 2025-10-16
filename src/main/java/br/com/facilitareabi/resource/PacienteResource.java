package br.com.facilitareabi.resource;

import br.com.facilitareabi.dto.PacienteRequest;
import br.com.facilitareabi.dto.PacienteResponse;
import br.com.facilitareabi.dto.UsuarioRequest;
import br.com.facilitareabi.dto.UsuarioResponse;
import br.com.facilitareabi.dao.PacienteDao;
import br.com.facilitareabi.model.Paciente;
import br.com.facilitareabi.service.PacienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
@Path("pacientes")
public class PacienteResource {

    PacienteDao pacienteDao = new PacienteDao();
    Paciente paciente = new Paciente();
    private PacienteService pacienteService = new PacienteService();

    //Criando métodos http
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPaciente(PacienteRequest request) {
        try {
            pacienteService.cadastrarPaciente(request);//chama o service resp por salvar no banco de dados

            PacienteResponse cadastrado = pacienteService.buscarPorNome(request.getNome());
            if (cadastrado != null){
                return Response.status(Response.Status.CREATED).build();
            } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Método para retornar pacientes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPacientes(){
        try{
            List<PacienteResponse> pacientes = pacienteService.listarPacientes();
            return Response.ok(pacientes).build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao listar pacientes: " + e.getMessage() + "\"}")
                    .build();
        }
    }
    //Método para retornar pacientes com base nome
    @GET
    @Path("/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyscarPacienteNome(@PathParam("nome")String nome){
        try{
            PacienteResponse pacienteResponse = pacienteService.buscarPorNome(nome);
            if (pacienteResponse != null){
                return Response.ok(pacienteResponse).build();
            }else
                return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"Paciente não encontrado\"}").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao buscar paciente: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    //PUT
    @PUT
    @Path("/{id_paciente}")
    public Response alterarPaciente(@PathParam("id_paciente") int id, PacienteRequest request){
        try{
            PacienteResponse atualizado = pacienteService.atualizarPaciente(request);
            return Response.ok().entity("Paciente atualizado!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar paciente: " + e.getMessage()).build();
        }
    }

    //Delete paciente
    @DELETE
    @Path("/{nome}")
    public Response deletarPaciente(@PathParam("nome")String nome){
        try{
            pacienteService.excluirPaciente(nome);
            return Response.ok().entity("Paciente excluído!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir paciente: " + e.getMessage()).build();
        }
    }




//    public void atualizarPaciente(Paciente paciente){
//        System.out.println("Deseja atualizar seu cadastro?");
//        String resp = scanner.nextLine();
//        if (resp.equalsIgnoreCase("Sim")){
//            if (paciente.getDataNascimento() == null) {
//                System.out.println("Digite sua data de nascimento (dd/MM/yyyy):");
//                String dataStr = scanner.nextLine();
//                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//                paciente.setDataNascimento(data);
//                cadastrarPaciente();
//                pacienteDao.atualizarPaciente(paciente);
//            }
//        }else{
//            System.out.println("Ok");
//        }
//    }
//    public void excluirPaciente(){
//        Paciente paciente = new Paciente();
//        System.out.println("Deseja excluir um paciente?");
//        String resp = scanner.nextLine();
//        if (resp.equalsIgnoreCase("Sim")){
//            System.out.println("Digite o nome do paciente que você deseja excluir: ");
//            String nomeExcluir = scanner.nextLine();
//            paciente.setNome(nomeExcluir);
//            pacienteDao.excluirPaciente(paciente.getNome());
//            System.out.println("Paciente excluído!");
//        }else {
//            System.out.println("Ok!");
//        }
//    }
}
