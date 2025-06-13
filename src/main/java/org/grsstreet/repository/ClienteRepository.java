package org.grsstreet.repository;

import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClienteRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public boolean buscarPorSenhaCliente(String senha) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            ClienteEntity cliente = session
                    .createQuery("FROM cliente WHERE senha = :senha", ClienteEntity.class)
                    .setParameter("senha", senha)
                    .uniqueResult();

            transaction.commit();

            return cliente != null;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao buscar cliente ", e);
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
