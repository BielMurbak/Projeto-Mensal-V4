package org.grsstreet.repository;

import org.grsstreet.model.user.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Classe responsável por realizar operações no banco de dados
 * relacionadas à entidade PessoaEntity utilizando Hibernate.
 */
public class PessoaRepository {

    // Fábrica de sessões do Hibernate (singleton)
    private static final SessionFactory sessionFactory;

    static {
        // Inicializa a SessionFactory com as configurações do hibernate.cfg.xml
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Salva uma nova pessoa no banco de dados.
     *
     * @param pessoa objeto PessoaEntity a ser salvo
     */
    public void salvar(PessoaEntity pessoa) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pessoa); // Insere no banco
        session.getTransaction().commit();
        session.close();
    }

    public List<PessoaEntity> listarSomenteClientes() {
        Session session = sessionFactory.openSession();
        List<PessoaEntity> clientes = session
                .createQuery("FROM PessoaEntity WHERE tipo = 'CLIENTE'", PessoaEntity.class)
                .getResultList();
        session.close();
        return clientes;
    }

    /**
     * Lista todas as pessoas cadastradas no banco de dados.
     *
     * @return Lista de objetos PessoaEntity
     */
    public List<PessoaEntity> listarTodosPessoas() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Busca todas as entidades PessoaEntity
            List<PessoaEntity> pessoas = session
                    .createQuery("FROM PessoaEntity", PessoaEntity.class)
                    .getResultList();

            transaction.commit();
            return pessoas;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao listar pessoas", e);
        } finally {
            session.close();
        }
    }
}
