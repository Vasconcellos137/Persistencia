package br.edu.ifpr.agenda.view;

import javax.naming.ldap.Control;

import br.edu.ifpr.agenda.controller.ContatoController;
import br.edu.ifpr.agenda.model.Contato;
import br.edu.ifpr.agenda.model.Endereco;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testando..");

        // Contato contato = new Contato();
        // ContatoController controller = new ContatoController();

        // contato.setNome("Nick");
        // contato.setCelular("(45) 998776-6565");
        // contato.setEmail("cowmorse@gmail.com");

        // controller.cadastrarContato(contato);

        Contato contato = new Contato();
        Endereco endereco = new Endereco();
        endereco.setRua("HightHell");
        endereco.setNumero("666");
        endereco.setCidade("Osaka");
        endereco.setEstado("Wellesy");

        ContatoController controller = new ContatoController();
        contato.setNome("Fulanin");
        contato.setCelular("(45 99987-6675)");
        contato.setEmail("157fulanin@gmail.com");
        contato.setEndereco(endereco);
        controller.cadastrarContato(contato);

    }
}