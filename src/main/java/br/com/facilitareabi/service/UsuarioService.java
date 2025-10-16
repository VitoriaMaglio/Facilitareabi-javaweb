package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.UsuarioDao;
import br.com.facilitareabi.dto.UsuarioLoginDto;
import br.com.facilitareabi.dto.UsuarioRequestDTO;
import br.com.facilitareabi.dto.UsuarioResponseDTO;
import br.com.facilitareabi.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

    public String autenticarUsuario(UsuarioLoginDto dto) throws SQLException {
        return usuarioDao.autenticarUsuario(dto.convertToUsuario(dto));
   }
    public void cadastrarUsuario(UsuarioRequestDTO usuarioDto) throws SQLException {
        usuarioDao.cadastrarUsuario(usuarioDto.convertToUsuario(usuarioDto));
        //chamar o dao para cadastrar usuário e converter os dados que vão entrar no banco em entidade
    }

    public UsuarioResponseDTO buscarLogin(String login) throws SQLException {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        return dto.convertToUsuarioResponseDto(usuarioDao.buscarLogin(login));
        //buscar dados da entidade no banco e retornar um dto na resposta para o usuário

    }

    public List<UsuarioResponseDTO> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        List<UsuarioResponseDTO> resposta = new ArrayList<>();
        for (Usuario u : usuarios) {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
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

    public UsuarioResponseDTO alterarUsuario(int id, UsuarioRequestDTO request) throws SQLException {
        Usuario usuario = new Usuario( id, request.getLogin(), request.getSenha(), request.getFeedback());
        usuarioDao.alterarUsuario(usuario);
        System.out.println("Usuário atualizado com sucesso!");

        return null;
    }

    // DELETE
    public void excluirUsua(String login) throws SQLException {
        usuarioDao.excluirUsua(login);
        System.out.println("Usuário excluído com sucesso!");
    }





}
