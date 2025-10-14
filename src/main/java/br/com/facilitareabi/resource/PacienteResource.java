package br.com.facilitareabi.resource;

import br.com.facilitareabi.dto.PacienteRequest;
import br.com.facilitareabi.dto.PacienteResponse;
import br.com.facilitareabi.dto.UsuarioRequest;
import br.com.facilitareabi.dto.UsuarioResponse;
import br.com.facilitareabi.dao.PacienteDao;
import br.com.facilitareabi.model.Paciente;
import br.com.facilitareabi.service.PacienteService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
@Path("pacientes/")
public class PacienteResource {

    PacienteDao pacienteDao = new PacienteDao();
    Paciente paciente = new Paciente();
    private PacienteService pacienteService = new PacienteService();

    //Criando métodos http
    @POST
    @Path("/cadastro/paciente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPaciente(PacienteRequest request) {
        try {
            pacienteService.cadastrarPaciente(request);//chama o service resp por salvar no banco de dados

            PacienteResponse cadastrado = pacienteService.buscarPorNome(paciente.getNome());
            //busca no banco o usuário para confirmar se foi salvo
            if (cadastrado.getNome().equals(paciente.getNome())) {
                //verifica se o nome do usuário retornado do banc é igual ao q foi enviado
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }




    public void atualizarPaciente(Paciente paciente){
        System.out.println("Deseja atualizar seu cadastro?");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("Sim")){
            if (paciente.getDataNascimento() == null) {
                System.out.println("Digite sua data de nascimento (dd/MM/yyyy):");
                String dataStr = scanner.nextLine();
                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                paciente.setDataNascimento(data);
                cadastrarPaciente();
                pacienteDao.atualizarPaciente(paciente);
            }
        }else{
            System.out.println("Ok");
        }
    }
    public void excluirPaciente(){
        Paciente paciente = new Paciente();
        System.out.println("Deseja excluir um paciente?");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("Sim")){
            System.out.println("Digite o nome do paciente que você deseja excluir: ");
            String nomeExcluir = scanner.nextLine();
            paciente.setNome(nomeExcluir);
            pacienteDao.excluirPaciente(paciente.getNome());
            System.out.println("Paciente excluído!");
        }else {
            System.out.println("Ok!");
        }
    }
}
