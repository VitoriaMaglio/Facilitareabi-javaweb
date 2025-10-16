package br.com.facilitareabi.resource;

import br.com.facilitareabi.dao.UsuarioDao;
import br.com.facilitareabi.dto.UsuarioLoginDto;
import br.com.facilitareabi.dto.UsuarioRequestDTO;
import br.com.facilitareabi.dto.UsuarioResponseDTO;
import br.com.facilitareabi.service.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;
/**
 * Classe representando um recurso REST para gerenciamento de usuários.
 * Expõem endpoints HTTP cadastro, autenticação, listagem, alteração e exclusão de usuários.
 *  @see UsuarioService
 *  @see UsuarioDao
 * */
@Path("usuarios")
public class UsuarioResource {
    private UsuarioService usuarioService = new UsuarioService();
    //Usuario usuario = new Usuario();
    UsuarioDao usuarioDao = new UsuarioDao();


    //Criando métodos http
    //registrar um novo usuário no sistema


    /**
     * Cadastra um novo recurso usuário no sistema.
     *
     * @param request Objeto DTO com dados do usuário (login, senha, feedback)
     * @return Response HTTP:
     *         - 201 CREATED se o usuário for cadastrado com sucesso
     *         - 400 BAD_REQUEST se houver inconsistência nos dados
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     * @throws SQLException se ocorrer erro de acesso ao banco de dados
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioRequestDTO request) {
        try {
            usuarioService.cadastrarUsuario(request);//chama o service resp por salvar no banco de dados

            UsuarioResponseDTO cadastrado = usuarioService.buscarLogin(request.getLogin());
            //busca no banco o usuário para confirmar se foi salvo
            if (cadastrado.getLogin().equals(request.getLogin())) {
                //verifica se o nome do usuário retornado do banc é igual ao q foi enviado
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //processo de autenticação;verifica as credenciais (login e senha) de um usuário já cadastrado.


    /**
     * Realiza a autenticação de um usuário já cadastrado.
     * @param usuario DTO contendo login e senha
     * @return Response HTTP:
     *         - 201 CREATED se a autenticação for bem-sucedida
     *         - 400 BAD_REQUEST se login ou senha forem inválidos
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     * @throws SQLException se ocorrer erro de acesso ao banco de dados
     */

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login (UsuarioLoginDto usuario){
        try {
            String mensagem = usuarioService.autenticarUsuario(usuario);

            if(mensagem.equals("Usuário logado com sucesso")){
                return Response.status(Response.Status.CREATED).build();
            }
            else if(mensagem.equals( "Usuário e/ou senha inválidos")){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao autenticar usuário  " + e.getMessage())
                    .build();
        }
        return null;
    }

    /**
     * Lista todos os usuários cadastrados no sistema.
     * @return Response HTTP:
     *         - 200 OK com a lista de usuários
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        try {
            List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();
            return Response.ok(usuarios).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao listar usuários: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Busca um usuário pelo login.
     * @param login Login do usuário a ser buscado
     * @return Response HTTP:
     *         - 200 OK com o usuário encontrado
     *         - 404 NOT_FOUND se o usuário não existir
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */

    @GET
    @Path("/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuarioPorId(@PathParam("login") String login) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarLogin(login);

            if (usuario != null) {
                return Response.ok(usuario).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"erro\": \"Usuário não encontrado\"}")
                        .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao buscar usuário: " + e.getMessage() + "\"}")
                    .build();
        }
    }
    //Put

    /**
     * Altera os dados de um usuário existente.
     * @param id ID do usuário a ser atualizado
     * @param request DTO contendo os novos dados do usuário
     * @return Response HTTP:
     *         - 200 OK com o usuário atualizado
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @PUT
    @Path("/{id}")
    public Response alterarUsuario(@PathParam("id") int id, UsuarioRequestDTO request) {
        try {
            UsuarioResponseDTO atualizado = usuarioService.alterarUsuario(id, request);
            return Response.ok(atualizado).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar usuário: " + e.getMessage()).build();
        }
    }


    // DELETE - Excluir usuário
    /**
     * Exclui um usuário do sistema.
     * @param login Login do usuário a ser excluído
     * @return Response HTTP:
     *         - 200 OK se a exclusão for bem-sucedida
     *         - 500 INTERNAL_SERVER_ERROR em caso de erro no banco
     */
    @DELETE
    @Path("/{login}")
    public Response excluirUsua(@PathParam("login") String login) {
        try {
            usuarioService.excluirUsua(login);
            return Response.ok().entity("Usuário excluído com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir usuário: " + e.getMessage()).build();
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
