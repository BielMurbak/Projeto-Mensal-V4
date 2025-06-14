package org.grsstreet.repository;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.product.ProdutoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ItemCarrinhoRepository {

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public ItemCarrinhoEntity salvar(ItemCarrinhoEntity item) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
            return item;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<ItemCarrinhoEntity> buscarItensPorCarrinho(CarrinhoEntity carrinho) {
        Session session = sessionFactory.openSession();
        List<ItemCarrinhoEntity> itens = session.createQuery("FROM ItemCarrinhoEntity i WHERE i.carrinho.id = :id", ItemCarrinhoEntity.class)
                .setParameter("id", carrinho.getId())
                .getResultList();
        session.close();
        return itens;
    }

    public void atualizar(ItemCarrinhoEntity item) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(item);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void deletar(ItemCarrinhoEntity item) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(item);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<ItemCarrinhoEntity> listarPorCarrinho(Long carrinhoId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM ItemCarrinhoEntity WHERE carrinho.id = :carrinhoId", ItemCarrinhoEntity.class)
                    .setParameter("carrinhoId", carrinhoId)
                    .list();
        }
    }

    // 🔄 Adiciona ou atualiza item do carrinho
    public void adicionarOuAtualizarItem(CarrinhoEntity carrinho, ProdutoEntity produto, int quantidade) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            ItemCarrinhoEntity itemExistente = session.createQuery(
                            "FROM ItemCarrinhoEntity WHERE carrinho.id = :carrinhoId AND produto.id = :produtoId",
                            ItemCarrinhoEntity.class)
                    .setParameter("carrinhoId", carrinho.getId())
                    .setParameter("produtoId", produto.getId())
                    .uniqueResult();

            if (itemExistente != null) {
                itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
                session.update(itemExistente);
            } else {
                ItemCarrinhoEntity novoItem = new ItemCarrinhoEntity();
                novoItem.setCarrinho(carrinho);
                novoItem.setProduto(produto);
                novoItem.setQuantidade(quantidade);
                session.save(novoItem);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}