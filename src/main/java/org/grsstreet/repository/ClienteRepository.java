package org.grsstreet.repository;

import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClienteRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public ClienteEntity buscarClientePorSenha(String senha) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

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


    public void deletarCpfCliente (String cpf){
            Session session = sessionFactory.openSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

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

    public List<ClienteEntity> listarTodosClientes() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

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


    public void salvar(ClienteEntity cliente) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cliente);
        session.getTransaction().commit();
        session.close();
    }
}
