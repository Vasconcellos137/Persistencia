package br.edu.ifpr.agenda.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import br.edu.ifpr.agenda.model.Contato;
import br.edu.ifpr.agenda.model.Endereco;

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

//Atualização
    public void updateSemContato(Contato contato){
        Connection cow = ConnectionFactory.getConnection();
        try {
            String sql = "upadate contatos set nome = ?, celular = ?, email = ?";
            PreparedStatement ps = cow.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getCelular());
            ps.setString(3, contato.getEmail());
            ps.executeUpdate();
            System.out.println("Contato atualizado com sucesso..");            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Contato contato){
        Connection cow = ConnectionFactory.getConnection();

        try {
            String sqlEndereco = "UPDATE enderecos SET rua = ?, numero = ?, cidade = ?, estado = ?";
            PreparedStatement psEndereco = cow.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
            psEndereco.setString(1, contato.getEndereco().getRua());
            psEndereco.setString(2, contato.getEndereco().getNumero());
            psEndereco.setString(3, contato.getEndereco().getCidade());
            psEndereco.setString(4, contato.getEndereco().getEstado());
            psEndereco.executeUpdate();

            ResultSet rs = psEndereco.getGeneratedKeys();
            int idEndereco = 0;
            if (rs.next())
                idEndereco = rs.getInt(1);

            String sql = "UPDATE contatos SET nome = ?, celular = ?, email = ?";
            PreparedStatement ps = cow.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getCelular());
            ps.setString(3, contato.getEmail());
            ps.setInt(4, idEndereco);
            ps.executeUpdate();
            System.out.println("Contato atualizado com sucesso..");            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//Deletar/Excluir

    public void delete(int id){
        Connection cow = ConnectionFactory.getConnection();
        try {
            String sqlEndereco = "DELETE FROM enderecos WHERE id = ?";
            PreparedStatement psEnd = cow.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
            psEnd.setInt(1, id); 
            psEnd.executeUpdate();

            String sql = "DELETE FROM contatos WHERE id = ?";
            PreparedStatement ps = cow.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Contato excluído com sucesso..");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteSemEndereco(int id){
        Connection cow = ConnectionFactory.getConnection();
        try {
            String sql = "DELETE FROM contatos WHERE id = ?";
            PreparedStatement ps = cow.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Contato excluído com sucesso..");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//Select/Selecionar 
    public ArrayList<Contato> selectSemEndereco(){
        Connection cow = ConnectionFactory.getConnection();
        ArrayList<Contato> contatoS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM contatos WHERE id = ?";
            PreparedStatement ps = cow.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("Id"));
                contato.setNome(rs.getString("nome"));
                contato.setCelular(rs.getString("celular"));
                contato.setEmail(rs.getString("email"));     
                contatoS.add(contato);                              
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return contatoS;
    }

    public LinkedList<Contato> select(){
        Connection cow = ConnectionFactory.getConnection();
        LinkedList<Contato> contatoS = new LinkedList<>();
        LinkedList<Endereco> enderecoS = new LinkedList();
        try {

            String sqlEndereco = "SELECT * FROM contatos WHERE id = ?";
            PreparedStatement psEndereco = cow.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsEnd = psEndereco.executeQuery();
            while (rsEnd.next()) {
                Endereco end = new Endereco();
                end.setRua(rsEnd.getString("rua"));
                end.setRua(rsEnd.getString("numero"));
                end.setRua(rsEnd.getString("cidade"));
                end.setRua(rsEnd.getString("estado"));
                enderecoS.add(end);
                                
            }

            ResultSet rs = psEndereco.getGeneratedKeys();
            int idEndereco = 0;
            if (rs.next())
                idEndereco = rs.getInt(1);


            String sql = "SELECT * FROM contatos WHERE id = ?";
            PreparedStatement ps = cow.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("Id"));
                contato.setNome(rs.getString("nome"));
                contato.setCelular(rs.getString("celular"));
                contato.setEmail(rs.getString("email"));     
                contatoS.add(contato);                              
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return contatoS;
    }
    
//JOIN entre tabelas    
    public LinkedList<Contato> tentativaJoin() {
        LinkedList<Contato> contatos = new LinkedList<>();
        String sqlContato = ("SELECT * FROM contatos JOIN enderecos ON enderecos.id = id_enderecos;"); 
        Connection cow = ConnectionFactory.getConnection(); // Abre conexão p sql

        try {
            PreparedStatement psContato = cow.prepareStatement(sqlContato);
            ResultSet rs = psContato.executeQuery();

            while (rs.next()){
                Endereco end = new Endereco();
                end.setId(rs.getInt("id_endereco"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getString("numero"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));

                Contato cont = new Contato();
                cont.setId(rs.getInt("id"));
                cont.setNome(rs.getString("nome"));
                cont.setCelular(rs.getString("celular"));
                cont.setEmail(rs.getString("email"));
                cont.setEndereco(end);
                contatos.add(cont);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatos;
    }
}


