package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.UsuarioDao;
import br.com.facilitareabi.dto.UsuarioLoginDto;
import br.com.facilitareabi.dto.UsuarioRequest;
import br.com.facilitareabi.dto.UsuarioResponse;
import br.com.facilitareabi.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

    public String autenticarUsuario(UsuarioLoginDto dto) throws SQLException {
        return usuarioDao.autenticarUsuario(dto.convertToUsuario(dto));
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

    public List<UsuarioResponse> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        List<UsuarioResponse> resposta = new ArrayList<>();
        for (Usuario u : usuarios) {
            UsuarioResponse dto = new UsuarioResponse();
            dto.setLogin(u.getLogin());
            dto.setLogin(u.getLogin());
            resposta.add(dto);
        }
        return resposta;
    }

//    public UsuarioResponse buscarUsuarioPorLogin(String login) throws SQLException {
//        Usuario usuario = usuarioDao.buscarLogin(login);
//        if (usuario != null) {
//            UsuarioResponse dto = new UsuarioResponse();
//            dto.setLogin(usuario.getLogin());
//            dto.setFeedback(usuario.getFeedback());
//            return dto;
//        } else {
//            return null;
//        }
//    }



}
