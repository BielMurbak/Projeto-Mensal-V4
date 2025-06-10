package org.grsstreet.repository;

import org.grsstreet.model.address.EnderecoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EnderecoRepository {


    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void salvar(EnderecoEntity endereco) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(endereco);
        session.getTransaction().commit();
        session.close();
    }

}
