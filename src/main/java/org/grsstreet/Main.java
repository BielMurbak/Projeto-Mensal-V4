package org.grsstreet;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.enums.TipoPessoa;
import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.grsstreet.view.TelaLogin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import javax.swing.SwingUtilities;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();


            ClienteEntity cliente = new ClienteEntity();
            PessoaEntity pessoaCliente = new PessoaEntity();
            EnderecoEntity endereco = new EnderecoEntity();

            AdministradorEntity adm = new AdministradorEntity();
            PessoaEntity pessoaAdm = new PessoaEntity();

            pessoaCliente.setTipo(TipoPessoa.CLIENTE_VAREJO);
            pessoaCliente.setNome("Adryan Jacinto");
            pessoaCliente.setCpf("279.641.580-40");
            pessoaCliente.setDataDeNascimento(LocalDate.of(2006, 7, 25));

            endereco.setRua("Jacinto");
            endereco.setBairro("Morumbi");
            endereco.setMunicipio("Foz do Iguaçu");
            endereco.setCep("85850-111");
            endereco.setEstado("PR");

            cliente.setSenha("2507@");
            cliente.setPessoa(pessoaCliente);
            cliente.setEnderecoEntity(endereco);

            pessoaAdm.setTipo(TipoPessoa.ADMINISTRADOR);
            pessoaAdm.setNome("adm");
            pessoaAdm.setCpf("093.777.222-01");
            pessoaAdm.setDataDeNascimento(LocalDate.of(2006, 7, 21));
            adm.setSenha("adm");


            adm.setPessoaEntity(pessoaAdm);

            session.save(cliente);
            session.save(adm);

            session.getTransaction().commit();

            // Fechar tudo
            session.close();
            sessionFactory.close();

        } catch (ConstraintViolationException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        } catch (HibernateException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
    }

        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
