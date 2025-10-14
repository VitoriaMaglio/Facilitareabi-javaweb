package br.com.facilitareabi.resource;

import br.facilitareabi.com.dao.UsuarioDao;
import br.facilitareabi.com.model.Usuario;
import br.facilitareabi.com.service.UsuarioService;

import java.util.Scanner;
public class UsuarioResource {
    private UsuarioService usuarioService = new UsuarioService();
    Usuario usuario = new Usuario();
    UsuarioDao usuarioDao = new UsuarioDao();
    Scanner scanner = new Scanner(System.in);
    public void cadastrarUsuario(){
        Usuario usuario = new Usuario();
        System.out.println("Digite seu login:");
        usuario.setLogin(scanner.nextLine());
        System.out.println("Digite sua senha:");
        usuario.setSenha(scanner.nextLine());
        usuarioService.cadastrarUsuario(usuario);

    }
    public void registrarFeedback(Usuario usuario) {
        System.out.println("Digite seu feedback: ");
        String feedback = scanner.nextLine();
        usuarioDao.atualizarFeedback(usuario.getId(), feedback);
        usuario.setFeedback(feedback); // também atualiza o objeto
    }
    public void buscarUsuario(Usuario usuario){
        System.out.println("Digite o login do usuário que você deseja buscar dados: ");
        String usuarioLogin = scanner.nextLine();
        Usuario usuarioEncontrado = usuarioDao.buscarLogin(usuarioLogin);
            if (usuarioLogin != null) {
                System.out.println("Usuário encontrado!");
                System.out.println("Login: " + usuarioEncontrado.getLogin());
                System.out.println("Senha: " + usuarioEncontrado.getSenha());
            } else {
                System.out.println("Usuário não encontrado.");
            }
    }
    public void atualizarUsuario(){
        System.out.println("Deseja atualizar seu usuário?");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("Sim")){
            cadastrarUsuario();
            usuarioDao.alterarUsuario(usuario);
        }else{
            System.out.println("Ok!");
        }
    }
    public void excluirUsuario(){
        UsuarioDao usuarioDao = new UsuarioDao();
        System.out.println("Deseja excluir um usuário?");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("Sim")){
                System.out.println("Digite o login do usuário que deseja excluir:");
                String login = scanner.nextLine();
                if (usuarioDao.existeUsuarioPorLogin(login)) {
                    usuarioDao.excluirUsua(login);
                } else {
                    System.out.println("Usuário com login '" + login + "' não encontrado no banco.");
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        }
    }
