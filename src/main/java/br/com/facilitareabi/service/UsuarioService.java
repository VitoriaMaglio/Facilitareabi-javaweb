package br.com.facilitareabi.service;
import br.facilitareabi.com.dao.UsuarioDao;
import br.facilitareabi.com.model.Usuario;
public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();
    public boolean verificarUsuario(Usuario usuario){
        return usuario.getSenha() != null && usuario.getLogin() != null;
    }
    public void cadastrarUsuario(Usuario usuario){
        if(verificarUsuario(usuario)){
            usuarioDao.cadastrarUsuario(usuario);
            System.out.printf("Usuário cadastrado com sucesso!");

        }else {
            System.out.printf("Usuário com dados inválidos");
        }
    }
}
