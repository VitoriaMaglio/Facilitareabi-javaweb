package br.com.facilitareabi.service;
import br.com.facilitareabi.dao.ConsultaDao;
import br.com.facilitareabi.dto.ConsultaRequestDTO;
import br.com.facilitareabi.dto.ConsultaResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
/**
 * Interface que define os métodos de serviço para a entidade Consulta.
 * Serve para abstrair a implementação e permitir injeção de dependências.
 */
public interface ConsultaService {

    /**
     * Busca uma consulta por data.
     *
     * @param data Data da consulta
     * @return DTO com os dados da consulta
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    ConsultaResponseDTO buscarPorData(LocalDate data) throws SQLException;

    /**
     * Cadastra uma nova consulta no banco.
     *
     * @param consultaRequestDTO DTO com os dados da consulta
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    void cadastrarConsulta(ConsultaRequestDTO consultaRequestDTO) throws SQLException;

    /**
     * Lista todas as consultas.
     *
     * @return Lista de DTOs de consultas
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    List<ConsultaResponseDTO> listarConsulta()  throws SQLException;

    /**
     * Atualiza uma consulta existente.
     *
     * @param id ID da consulta
     * @param request DTO com os dados atualizados
     * @return DTO da consulta atualizada
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    ConsultaResponseDTO atualizarConsulta(int id, ConsultaRequestDTO request) throws SQLException;

    /**
     * Exclui uma consulta pelo ID.
     *
     * @param id ID da consulta
     * @throws SQLException Caso ocorra erro de acesso ao banco
     */
    void excluirConsultaData(int id) throws SQLException;
}
