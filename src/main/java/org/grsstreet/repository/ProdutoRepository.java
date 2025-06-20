package org.grsstreet.repository;

import org.grsstreet.model.enums.TipoProduto;
import org.grsstreet.model.product.ProdutoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    public void atualizar(ProdutoEntity produto) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(produto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void deletarNomeProduto(String nome) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            ProdutoEntity produto = session
                    .createQuery("FROM produto WHERE nome = :nome", ProdutoEntity.class)
                    .setParameter("nome", nome)
                    .uniqueResult();

            if (produto != null) {
                session.delete(produto);
                transaction.commit();
                System.out.println("Produto deletado com sucesso.");
            } else {
                System.out.println("Produto não encontrado.");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public List<ProdutoEntity> buscarProdutosComPrecoMaximo() {
        Session session = sessionFactory.openSession();

        // 1. Primeiro, buscar o preço máximo na tabela
        Double precoMaximo = session.createQuery("SELECT MAX(p.preco) FROM ProdutoEntity p", Double.class)
                .getSingleResult();

        // 2. Agora, buscar todos os produtos que têm esse preço máximo
        List<ProdutoEntity> produtos = session.createQuery(
                        "FROM ProdutoEntity p WHERE p.preco = :precoMax", ProdutoEntity.class)
                .setParameter("precoMax", precoMaximo)
                .getResultList();

        session.close();
        return produtos;
    }

    public List<ProdutoEntity> buscarPorParcialNome(String nome) {
        Session session = sessionFactory.openSession();
        List<ProdutoEntity> produtos = session.createQuery(
                        "FROM ProdutoEntity WHERE LOWER(nome) LIKE LOWER(:nome)", ProdutoEntity.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
        session.close();
        return produtos;
    }

}
