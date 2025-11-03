package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpr.agenda.model.Contato;

//P/ cada comando Sql que precisar executar, terá uma "linha" aq tbm..
//Esse recurso é um CRUD - Create, Read, Update e Delete. 

public class ContatoDAO {

    // Create - C
    public void salvarSemEndereco(Contato contato) {
        String sqlContato = ("INSERT INTO contatos(nome, celular, email) VALUES(?, ?, ?)"); // É como se fosse o insert
                                                                                            // normal do BD, mas
                                                                                            // mesclado com java
        Connection cow = ConnectionFactory.getConnection(); // Abre conexão p sql

        try {
            PreparedStatement psContato = cow.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular()); // Etapa onde é realmente inserido lá no BD as informações
            psContato.setString(3, contato.getEmail());
            psContato.executeUpdate();
            System.out.println("Contato inserido com sucesso..");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Contato contato) {
        Connection cow = ConnectionFactory.getConnection();

        // P/ inserir endereço 1°
        try { // Posições atributos da tabela normal 0, 1, 2..
            String sqlEndereco = "INSERT INTO enderecos(rua, numero, cidade, estado) VALUES(?, ?, ?, ?)";
            PreparedStatement psEndereco = cow.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
            psEndereco.setString(1, contato.getEndereco().getRua());
            psEndereco.setString(2, contato.getEndereco().getNumero());
            psEndereco.setString(3, contato.getEndereco().getCidade());
            psEndereco.setString(4, contato.getEndereco().getEstado());
            psEndereco.executeUpdate();

            ResultSet rs = psEndereco.getGeneratedKeys();
            int idEndereco = 0;
            if (rs.next())
                idEndereco = rs.getInt(1); // Pega 0, o 1° atributo da tabela //Se retorna 0 é pq não deu certo, pq
                                           // retorna sempre no 1
                                           // P/ ResultSet os atributos iniciam em 1, 2, 3..

            // Inserir contato
            String sqlContato = "INSERT INTO contatos(nome, celular, email, id_endereco) VALUES (?, ?, ?, ?)";
            PreparedStatement psContato = cow.prepareStatement(sqlContato);
            psContato.setString(1, contato.getNome());
            psContato.setString(2, contato.getCelular());
            psContato.setString(3, contato.getEmail());
            psContato.setInt(4, idEndereco);
            psContato.executeUpdate();
            System.out.println("Contato inserido com sucesso..");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
