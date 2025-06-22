package org.grsstreet.repository;

import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Repositório responsável por realizar operações no banco de dados
 * relacionadas à entidade ClienteEntity usando Hibernate.
 */
public class ClienteRepository {

    // Instância única de SessionFactory para abrir sessões com o banco de dados
    private static final SessionFactory sessionFactory;

    static {
        // Carrega as configurações do Hibernate (hibernate.cfg.xml) e cria a SessionFactory
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Busca um cliente no banco de dados pela senha.
     *
     * @param senha senha do cliente
     * @return ClienteEntity correspondente ou null se não encontrar
     */
    public ClienteEntity buscarClientePorSenha(String senha) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Consulta o cliente com a senha fornecida
            ClienteEntity cliente = session
                    .createQuery("FROM ClienteEntity WHERE senha = :senha", ClienteEntity.class)
                    .setParameter("senha", senha)
                    .uniqueResult();

            transaction.commit();
            return cliente;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao buscar cliente ", e);
        } finally {
            session.close();
        }
    }

    /**
     * Deleta um cliente do banco com base no CPF vinculado à entidade Pessoa.
     *
     * @param cpf CPF do cliente a ser deletado
     */
    public void deletarCpfCliente(String cpf) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Consulta o cliente pelo CPF da entidade Pessoa
            ClienteEntity cliente = session
                    .createQuery("FROM cliente c WHERE c.pessoa.cpf = :cpf", ClienteEntity.class)
                    .setParameter("cpf", cpf)
                    .uniqueResult();

            if (cliente != null) {
                session.delete(cliente);
                transaction.commit();
                System.out.println("Cliente deletado com sucesso.");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    /**
     * Lista todos os clientes cadastrados no banco, incluindo suas informações de pessoa e endereço.
     *
     * @return Lista de clientes
     */
    public List<ClienteEntity> listarTodosClientes() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Recupera todos os clientes com fetch nas entidades relacionadas
            List<ClienteEntity> clientes = session
                    .createQuery("FROM ClienteEntity a JOIN FETCH a.pessoa JOIN FETCH a.enderecoEntity", ClienteEntity.class)
                    .getResultList();

            transaction.commit();
            return clientes;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao listar clientes", e);
        } finally {
            session.close();
        }
    }

    /**
     * Salva um novo cliente no banco de dados.
     *
     * @param cliente Cliente a ser salvo
     */
    public void salvar(ClienteEntity cliente) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cliente);
        session.getTransaction().commit();
        session.close();
    }
}
