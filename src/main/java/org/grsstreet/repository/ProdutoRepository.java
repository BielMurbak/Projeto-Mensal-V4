package org.grsstreet.repository;

import org.grsstreet.model.enums.TipoProduto;
import org.grsstreet.model.product.ProdutoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProdutoRepository {
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<ProdutoEntity> listarTodos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ProdutoEntity", ProdutoEntity.class).list();
        }
    }

    public List<ProdutoEntity> listarTenis() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ProdutoEntity p WHERE p.tipo = :tipoProduto", ProdutoEntity.class)
                    .setParameter("tipoProduto", TipoProduto.TENIS)
                    .list();
        }
    }

    public void salvar(ProdutoEntity produto) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(produto);
            session.getTransaction().commit();
        }
    }
}