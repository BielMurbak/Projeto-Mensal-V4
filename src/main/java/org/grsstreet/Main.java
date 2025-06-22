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

/**
 * Classe principal da aplicação, responsável por iniciar o sistema,
 * criar entidades iniciais no banco de dados e abrir a tela de login.
 */
public class Main {

    /**
     * Método principal que inicializa a aplicação.
     *
     * - Configura e abre uma sessão Hibernate para persistência.
     * - Cria dados iniciais (clientes, administradores, produtos, endereços).
     * - Persiste os dados no banco.
     * - Trata possíveis exceções do banco.
     * - Abre a interface gráfica da aplicação (TelaLogin).
     *
     * @param args argumentos da linha de comando (não usados)
     */
    public static void main(String[] args) {

        try {
            // Configuração e abertura da sessão Hibernate
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            // Criando entidades Cliente, Pessoa, Endereço
            ClienteEntity cliente = new ClienteEntity();
            PessoaEntity pessoaCliente = new PessoaEntity();
            EnderecoEntity endereco = new EnderecoEntity();

            // Criando entidades Administrador e Pessoa associada
            AdministradorEntity adm = new AdministradorEntity();
            PessoaEntity pessoaAdm = new PessoaEntity();

            // Configurando dados do cliente
            pessoaCliente.setTipo(TipoPessoa.CLIENTE);
            pessoaCliente.setNome("biel");
            pessoaCliente.setCpf("279.641.580-40");
            pessoaCliente.setDataDeNascimento(LocalDate.of(2006, 7, 25));

            endereco.setRua("Jacinto");
            endereco.setBairro("Morumbi");
            endereco.setMunicipio("Foz do Iguaçu");
            endereco.setCep("85850-111");
            endereco.setEstado("PR");

            cliente.setSenha("123");
            cliente.setPessoa(pessoaCliente);
            cliente.setEnderecoEntity(endereco);

            // Configurando dados do administrador
            pessoaAdm.setTipo(TipoPessoa.ADMINISTRADOR);
            pessoaAdm.setNome("adm");
            pessoaAdm.setCpf("093.777.222-01");
            pessoaAdm.setDataDeNascimento(LocalDate.of(2006, 7, 21));
            adm.setSenha("adm");

            // Criando produtos iniciais
            ProdutoEntity produto1 = new ProdutoEntity(TipoProduto.TENIS, "Adidas SuperStar", 30, 499.99, "src/main/resources/Imagens/Adidas SuperStar.jpg");
            ProdutoEntity produto2 = new ProdutoEntity(TipoProduto.TENIS, "Nike AirForce 1", 40, 799.99, "src/main/resources/Imagens/Nike Air Force 1.jpg");
            ProdutoEntity produto3 = new ProdutoEntity(TipoProduto.TENIS, "Puma Smash V2", 10, 439.99, "src/main/resources/Imagens/Puma Smash V2.jpg");
            ProdutoEntity produto4 = new ProdutoEntity(TipoProduto.TENIS, "New Balance 550", 22, 799.99, "src/main/resources/Imagens/New Balance 550.jpg");
            ProdutoEntity produto5 = new ProdutoEntity(TipoProduto.TENIS, "Fila Fitness Tennis Club", 34, 349.99, "src/main/resources/Imagens/Fila Fitnnes Tennis Club.jpg");
            ProdutoEntity produto6 = new ProdutoEntity(TipoProduto.TENIS, "Adidas Campus 00s", 70, 699.99, "src/main/resources/Imagens/Adidas Campus 00s.jpg");

            adm.setPessoaEntity(pessoaAdm);

            // Lista de produtos para persistir
            List<ProdutoEntity> produtos = List.of(produto1, produto2, produto3, produto4, produto5, produto6);

            // Salvando produtos no banco
            for (ProdutoEntity p : produtos) {
                session.save(p);
            }

            // Salvando cliente e administrador no banco
            session.save(cliente);
            session.save(adm);

            // Commit da transação
            session.getTransaction().commit();

            // Fechando sessão e fábrica de sessões
            session.close();
            sessionFactory.close();

        } catch (ConstraintViolationException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        } catch (HibernateException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }

        // Inicializa e exibe a tela de login da aplicação no Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
