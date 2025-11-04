package br.edu.ifpr.agenda.controller;

import br.edu.ifpr.agenda.model.Contato;
import br.edu.ifpr.agenda.model.dao.ContatoDAO;

public class ContatoController {
    private ContatoDAO dao;

    public ContatoController(){
        this.dao = new ContatoDAO();
    }

    public void cadastrarContato(Contato contato){
        if (contato.getNome() == null || contato.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio");
            return;
        }

        dao.salvar(contato);
    }

    public void atualizarContato(Contato contato){
        if (contato.getNome() == null || contato.getNome().isEmpty()) {
            System.out.println("Nome não pode ser vazio");
            return;
        }

        if (contato.getId() <= 0) {
            System.out.println("Id inválido");
            return;
        }

        dao.update(contato);
    }

    public void deletarContato(int id){
        if (id <= 0) {
            System.out.println("Id inválido");
            return;
        }

        dao.delete(id);
    }

    public void selectContato(Contato contato){
        return dao.select();
    }

}
