package br.com.facilitareabi.resource;

import br.com.facilitareabi.dao.UsuarioDao;
import br.com.facilitareabi.dto.UsuarioRequest;
import br.com.facilitareabi.dto.UsuarioResponse;
import br.com.facilitareabi.model.Usuario;
import br.com.facilitareabi.service.UsuarioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioResource {
    private UsuarioService usuarioService = new UsuarioService();
    Usuario usuario = new Usuario();
    UsuarioDao usuarioDao = new UsuarioDao();


    //Criando métodos http
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioRequest request) {
        try {
            usuarioService.cadastrarUsuario(request);//chama o service resp por salvar no banco de dados

            UsuarioResponse cadastrado = usuarioService.buscarLogin(usuario.getLogin());
            //busca no banco o usuário para confirmar se foi salvo
            if (cadastrado.getLogin().equals(usuario.getLogin())) {
                //verifica se o nome do usuário retornado do banc é igual ao q foi enviado
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }




//    public void cadastrarUsuario(){
//        Usuario usuario = new Usuario();
//        System.out.println("Digite seu login:");
//        usuario.setLogin(scanner.nextLine());
//        System.out.println("Digite sua senha:");
//        usuario.setSenha(scanner.nextLine());
//        usuarioService.cadastrarUsuario(usuario);
//
//    }
//    public void registrarFeedback(Usuario usuario) {
//        System.out.println("Digite seu feedback: ");
//        String feedback = scanner.nextLine();
//        usuarioDao.atualizarFeedback(usuario.getId(), feedback);
//        usuario.setFeedback(feedback); // também atualiza o objeto
//    }
//    public void buscarUsuario(Usuario usuario){
//        System.out.println("Digite o login do usuário que você deseja buscar dados: ");
//        String usuarioLogin = scanner.nextLine();
//        Usuario usuarioEncontrado = usuarioDao.buscarLogin(usuarioLogin);
//            if (usuarioLogin != null) {
//                System.out.println("Usuário encontrado!");
//                System.out.println("Login: " + usuarioEncontrado.getLogin());
//                System.out.println("Senha: " + usuarioEncontrado.getSenha());
//            } else {
//                System.out.println("Usuário não encontrado.");
//            }
//    }
//    public void atualizarUsuario(){
//        System.out.println("Deseja atualizar seu usuário?");
//        String resp = scanner.nextLine();
//        if (resp.equalsIgnoreCase("Sim")){
//            cadastrarUsuario();
//            usuarioDao.alterarUsuario(usuario);
//        }else{
//            System.out.println("Ok!");
//        }
//    }
//    public void excluirUsuario(){
//        UsuarioDao usuarioDao = new UsuarioDao();
//        System.out.println("Deseja excluir um usuário?");
//        String resp = scanner.nextLine();
//        if (resp.equalsIgnoreCase("Sim")){
//                System.out.println("Digite o login do usuário que deseja excluir:");
//                String login = scanner.nextLine();
//                if (usuarioDao.existeUsuarioPorLogin(login)) {
//                    usuarioDao.excluirUsua(login);
//                } else {
//                    System.out.println("Usuário com login '" + login + "' não encontrado no banco.");
//                }
//            } else {
//                System.out.println("Operação cancelada.");
//            }
//        }
    }
