package org.grsstreet.repository;


import org.grsstreet.model.user.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PessoaRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }


    public void salvar(PessoaEntity pessoa ) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pessoa);
        session.getTransaction().commit();
        session.close();
    }

    public List<PessoaEntity> listarTodosPessoas() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            List<PessoaEntity> pessoas = session
                    .createQuery("FROM PessoaEntity", PessoaEntity.class)
                    .getResultList();

            transaction.commit();

            return pessoas;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao listar pessoas", e);
        } finally {
            session.close();
        }
    }



}
