package org.grsstreet.repository;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

    public List<EnderecoEntity> listarEnderecoPessoas() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            List<EnderecoEntity> endereco = session
            .createQuery("FROM endereco", EnderecoEntity.class)
                    .getResultList();

            transaction.commit();

            return endereco;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao listar endereco", e);
        } finally {
            session.close();
        }
    }

}
