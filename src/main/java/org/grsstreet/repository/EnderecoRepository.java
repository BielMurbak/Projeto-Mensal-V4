package org.grsstreet.repository;

import org.grsstreet.model.address.EnderecoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Classe responsável por realizar operações de persistência (salvar e listar)
 * relacionadas à entidade EnderecoEntity, usando Hibernate.
 */
public class EnderecoRepository {

    // Instância estática da fábrica de sessões do Hibernate
    private static final SessionFactory sessionFactory;

    static {
        // Carrega as configurações do Hibernate (hibernate.cfg.xml) e constrói a fábrica de sessões
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Salva um novo endereço no banco de dados.
     *
     * @param endereco objeto EnderecoEntity a ser salvo
     */
    public void salvar(EnderecoEntity endereco) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(endereco); // Insere o endereço na base
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Lista todos os endereços cadastrados no banco.
     *
     * @return Lista de objetos EnderecoEntity
     */
    public List<EnderecoEntity> listarEnderecoPessoas() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Busca todos os registros da tabela EnderecoEntity
            List<EnderecoEntity> endereco = session
                    .createQuery("FROM endereco", EnderecoEntity.class) // <-- Provavelmente deveria ser "FROM EnderecoEntity"
                    .getResultList();

            transaction.commit();
            return endereco;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao listar endereços", e);
        } finally {
            session.close();
        }
    }
}
