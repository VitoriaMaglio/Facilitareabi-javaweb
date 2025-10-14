package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.UsuarioDao;
import br.com.facilitareabi.dto.UsuarioRequest;
import br.com.facilitareabi.dto.UsuarioResponse;
import br.com.facilitareabi.model.Usuario;

import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

    public boolean verificarUsuario(Usuario usuario){
        return usuario.getSenha() != null && usuario.getLogin() != null;
    }
    public void cadastrarUsuario(UsuarioRequest usuarioDto) throws SQLException {
        usuarioDao.cadastrarUsuario(usuarioDto.convertToUsuario(usuarioDto));
        //chamar o dao para cadastrar usuário e converter os dados que vão entrar no banco em entidade
    }

    public UsuarioResponse buscarLogin(String login) throws SQLException {
        UsuarioResponse dto = new UsuarioResponse();
        return dto.convertToUsuarioResponseDto(usuarioDao.buscarLogin(login));
        //buscar dados da entidade no banco e retornar um dto na resposta para o usuário
    }

}
