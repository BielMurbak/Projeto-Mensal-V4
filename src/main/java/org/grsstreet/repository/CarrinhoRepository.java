package org.grsstreet.repository;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CarrinhoRepository {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public CarrinhoEntity buscarCarrinhoAbertoPorClienteId(Long clienteId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM CarrinhoEntity c WHERE c.cliente.id = :clienteId AND c.finalizado = false";
            return session.createQuery(hql, CarrinhoEntity.class)
                    .setParameter("clienteId", clienteId)
                    .uniqueResult();
        }
    }

    public CarrinhoEntity salvarOuAtualizar(CarrinhoEntity carrinho) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(carrinho);
            tx.commit();
            return carrinho;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<CarrinhoEntity> listarTodos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM CarrinhoEntity", CarrinhoEntity.class).list();
        }
    }
}