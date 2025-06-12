package org.grsstreet;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.enums.TipoPessoa;

import org.grsstreet.model.enums.TipoProduto;
import org.grsstreet.model.product.ProdutoEntity;
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
import java.util.List;

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

            ProdutoEntity produto1 = new ProdutoEntity(TipoProduto.TENIS, "Adidas SuperStar", 30, 499.99, "src/main/resources/Imagens/Adidas SuperStar.jpg");
            ProdutoEntity produto2 = new ProdutoEntity(TipoProduto.TENIS, "Nike AirForce 1", 40, 799.99, "src/main/resources/Imagens/Nike Air Force 1.jpg");
            ProdutoEntity produto3 = new ProdutoEntity(TipoProduto.TENIS, "Puma Smash V2", 10, 439.99, "src/main/resources/Imagens/Puma Smash V2.jpg");
            ProdutoEntity produto4 = new ProdutoEntity(TipoProduto.TENIS, "New Balance 550", 22, 799.99, "src/main/resources/Imagens/New Balance 550.jpg");
            ProdutoEntity produto5 = new ProdutoEntity(TipoProduto.TENIS, "Fila Fitness Tennis Club", 34, 349.99, "src/main/resources/Imagens/Fila Fitnnes Tennis Club.jpg");
            ProdutoEntity produto6 = new ProdutoEntity(TipoProduto.TENIS, "Adidas Campus 00s", 70, 699.99, "src/main/resources/Imagens/Adidas Campus 00s.jpg");

            adm.setPessoaEntity(pessoaAdm);

            List<ProdutoEntity> produtos = List.of(produto1, produto2, produto3, produto4, produto5, produto6);
            for (ProdutoEntity p : produtos) {
                session.save(p);
            }
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
