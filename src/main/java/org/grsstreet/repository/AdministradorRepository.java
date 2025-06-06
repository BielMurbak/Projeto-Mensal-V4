package org.grsstreet.repository;

import org.grsstreet.model.user.AdministradorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AdministradorRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public boolean buscarPorSenha(String senha) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            AdministradorEntity admin = session
                    .createQuery("FROM AdministradorEntity WHERE senha = :senha", AdministradorEntity.class)
                    .setParameter("senha", senha)
                    .uniqueResult();

            transaction.commit();

            return admin != null;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao buscar Administrador por senha", e);
        } finally {
            session.close();
        }
    }

}
