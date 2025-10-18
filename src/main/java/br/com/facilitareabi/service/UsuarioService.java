package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.UsuarioDao;
import br.com.facilitareabi.dto.UsuarioLoginDto;
import br.com.facilitareabi.dto.UsuarioRequestDTO;
import br.com.facilitareabi.dto.UsuarioResponseDTO;
import br.com.facilitareabi.model.Usuario;
import br.com.facilitareabi.security.PasswordHash;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private final UsuarioDao usuarioDao;

    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public String autenticarUsuario(UsuarioLoginDto dto) throws SQLException {
        return usuarioDao.autenticarUsuario(dto.convertToUsuario(dto));
   }

    public void cadastrarUsuario(UsuarioRequestDTO usuarioDto) throws SQLException {
        if (usuarioDto.getLogin() == null ){
            throw new IllegalArgumentException("Login é obrigatório");
        }
        String senhaCriptografada = PasswordHash.hashPassword(usuarioDto.getSenha());
        usuarioDao.cadastrarUsuario(usuarioDto.convertToUsuario(usuarioDto));

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

    public UsuarioResponseDTO alterarUsuario(int id, UsuarioRequestDTO request) throws SQLException {
        Usuario usuario = new Usuario( id, request.getLogin(), request.getSenha(), request.getFeedback());
        usuarioDao.alterarUsuario(usuario);
        System.out.println("Usuário atualizado com sucesso!");

        return null;
    }

    public void excluirUsua(String login) throws SQLException {
        usuarioDao.excluirUsua(login);
        System.out.println("Usuário excluído com sucesso!");
    }

}
