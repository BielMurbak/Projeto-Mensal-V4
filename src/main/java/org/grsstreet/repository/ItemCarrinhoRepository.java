package org.grsstreet.repository;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.product.ProdutoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Repositório responsável pelas operações no banco de dados
 * relacionadas aos itens do carrinho (ItemCarrinhoEntity).
 */
public class ItemCarrinhoRepository {

    // Criação da SessionFactory a partir das configurações do Hibernate
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /**
     * Salva um novo item no banco de dados.
     *
     * @param item ItemCarrinhoEntity a ser salvo
     * @return o item salvo
     */
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

    /**
     * Busca todos os itens de um carrinho específico.
     *
     * @param carrinho CarrinhoEntity cujo ID será usado na consulta
     * @return Lista de itens pertencentes ao carrinho
     */
    public List<ItemCarrinhoEntity> buscarItensPorCarrinho(CarrinhoEntity carrinho) {
        Session session = sessionFactory.openSession();
        List<ItemCarrinhoEntity> itens = session.createQuery(
                        "FROM ItemCarrinhoEntity i WHERE i.carrinho.id = :id", ItemCarrinhoEntity.class)
                .setParameter("id", carrinho.getId())
                .getResultList();
        session.close();
        return itens;
    }

    /**
     * Atualiza um item já existente no banco.
     *
     * @param item Item a ser atualizado
     */
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

    /**
     * Deleta um item do carrinho.
     *
     * @param item Item a ser removido
     */
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

    /**
     * Lista todos os itens de um carrinho pelo ID do carrinho.
     *
     * @param carrinhoId ID do carrinho
     * @return Lista de itens
     */
    public List<ItemCarrinhoEntity> listarPorCarrinho(Long carrinhoId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM ItemCarrinhoEntity WHERE carrinho.id = :carrinhoId", ItemCarrinhoEntity.class)
                    .setParameter("carrinhoId", carrinhoId)
                    .list();
        }
    }

    /**
     * Adiciona um novo item ao carrinho ou atualiza a quantidade se ele já existir.
     *
     * @param carrinho  Carrinho ao qual o item pertence
     * @param produto   Produto a ser adicionado
     * @param quantidade Quantidade do produto
     */
    public void adicionarOuAtualizarItem(CarrinhoEntity carrinho, ProdutoEntity produto, int quantidade) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // Verifica se o item já existe no carrinho
            ItemCarrinhoEntity itemExistente = session.createQuery(
                            "FROM ItemCarrinhoEntity WHERE carrinho.id = :carrinhoId AND produto.id = :produtoId",
                            ItemCarrinhoEntity.class)
                    .setParameter("carrinhoId", carrinho.getId())
                    .setParameter("produtoId", produto.getId())
                    .uniqueResult();

            if (itemExistente != null) {
                // Se já existir, apenas soma a quantidade
                itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
                session.update(itemExistente);
            } else {
                // Caso contrário, cria um novo item no carrinho
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
