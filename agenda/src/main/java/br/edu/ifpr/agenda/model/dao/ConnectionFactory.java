package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conexao;

    private ConnectionFactory() {

    }

    public static Connection getConnection() {
        try {
            if (conexao == null) {
                // jdbc:gbdb://ip do servidor do BD: porta/database
                String url = "jdbc:mysql://localhost:3306/agenda";   //localhost:3306   // <-- Aqui tbm
                String user = "aluno";      //root                                       // <-- Muda conforme a máquina q tu está a usar
                String password = "aluno";     //root                                    // <-- Esse tbm muda
                conexao = DriverManager.getConnection(url, user, password);             //Tu precisas trocar o nome de acondo com o MySql do PC
                System.out.println("Tás conectadii bixa !!!");
            }
        } catch (SQLException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conexao;
    }
}
