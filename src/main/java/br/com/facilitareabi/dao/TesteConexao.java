package br.com.facilitareabi.dao;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {
        Connection conn = ConnectionFactory.obterConexao();
        if (conn != null) {
            System.out.println("Conexão com Oracle bem-sucedida!");
        } else {
            System.out.println(" Falha na conexão!");
        }
    }

}
