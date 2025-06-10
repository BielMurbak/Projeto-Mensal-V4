package org.grsstreet.repository;


import org.grsstreet.model.user.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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


}
