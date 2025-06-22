package org.grsstreet.repository;

import org.grsstreet.model.enums.TipoProduto;
import org.grsstreet.model.product.ProdutoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Classe responsável por realizar operações no banco de dados
 * para a entidade ProdutoEntity utilizando Hibernate.
 */
public class ProdutoRepository {

    // Fábrica de sessões do Hibernate (singleton)
    private static final SessionFactory sessionFactory;

    static {
        // Inicializa a SessionFactory a partir do hibernate.cfg.xml
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Lista todos os produtos cadastrados.
     *
     * @return Lista de ProdutoEntity
     */
    public List<ProdutoEntity> listarTodos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ProdutoEntity", ProdutoEntity.class).list();
        }
    }

    /**
     * Lista apenas os produtos do tipo TÊNIS.
     *
     * @return Lista de ProdutoEntity filtrados por tipo
     */
    public List<ProdutoEntity> listarTenis() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM ProdutoEntity p WHERE p.tipo = :tipoProduto", ProdutoEntity.class)
                    .setParameter("tipoProduto", TipoProduto.TENIS)
                    .list();
        }
    }

    /**
     * Salva um novo produto no banco.
     *
     * @param produto ProdutoEntity a ser salvo
     */
    public void salvar(ProdutoEntity produto) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(produto);
            session.getTransaction().commit();
        }
    }

    /**
     * Atualiza um produto existente.
     *
     * @param produto ProdutoEntity a ser atualizado
     */
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

    /**
     * Deleta um produto com base no nome.
     *
     * @param nome Nome do produto a ser deletado
     */
    public void deletarNomeProduto(String nome) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // ⚠️ Correção importante: o nome da entidade deve estar correto (ProdutoEntity)
            ProdutoEntity produto = session
                    .createQuery("FROM ProdutoEntity WHERE nome = :nome", ProdutoEntity.class)
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

    /**
     * Busca produtos com preço menor ou igual ao valor informado.
     *
     * @param precoMaximo valor máximo do preço
     * @return Lista de produtos encontrados
     */
    public List<ProdutoEntity> buscarProdutosComPrecoMaximo(double precoMaximo) {
        Session session = sessionFactory.openSession();

        List<ProdutoEntity> produtos = session.createQuery(
                        "FROM ProdutoEntity p WHERE p.preco <= :precoMax", ProdutoEntity.class)
                .setParameter("precoMax", precoMaximo)
                .getResultList();

        session.close();
        return produtos;
    }

    /**
     * Conta a quantidade total de produtos cadastrados.
     *
     * @return número total de produtos
     */
    public long contarProdutos() {
        Session session = sessionFactory.openSession();
        long total = (long) session.createQuery(
                "SELECT COUNT(p) FROM ProdutoEntity p").getSingleResult();
        session.close();
        return total;
    }

    /**
     * Busca produtos que contenham parte do nome informado (busca parcial, case-insensitive).
     *
     * @param nome parte do nome
     * @return Lista de produtos que correspondem
     */
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
