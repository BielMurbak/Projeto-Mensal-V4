package org.grsstreet.repository;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CarrinhoRepository {

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /**
     * Busca um carrinho ativo (não finalizado) para o cliente informado..
     */
    public CarrinhoEntity buscarCarrinhoAtivoPorCliente(ClienteEntity cliente) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM CarrinhoEntity WHERE cliente.id = :clienteId AND finalizado = false",
                            CarrinhoEntity.class)
                    .setParameter("clienteId", cliente.getId())
                    .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar carrinho ativo para o cliente", e);
        }
    }

    /**
     * Salva um novo carrinho no banco de dados.
     */
    public CarrinhoEntity salvar(CarrinhoEntity carrinho) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(carrinho);
            tx.commit();
            return carrinho;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Erro ao salvar carrinho", e);
        }
    }

    public CarrinhoEntity buscarCarrinhoAtivoPorClienteComItens(ClienteEntity cliente) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT c FROM CarrinhoEntity c LEFT JOIN FETCH c.itens WHERE c.cliente.id = :clienteId AND c.finalizado = false",
                            CarrinhoEntity.class)
                    .setParameter("clienteId", cliente.getId())
                    .uniqueResult();
        }
    }

    /**
     * Atualiza um carrinho existente no banco (por exemplo, para marcar como finalizado).
     */
    public void atualizar(CarrinhoEntity carrinho) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(carrinho);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Erro ao atualizar carrinho", e);
        }
    }
}