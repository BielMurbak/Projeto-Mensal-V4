package org.grsstreet.repository;

import org.grsstreet.model.user.AdministradorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.util.List;

/**
 * Repositório para operações CRUD da entidade AdministradorEntity.
 * Utiliza Hibernate para manipulação do banco de dados.
 */
public class AdministradorRepository {

    private static final SessionFactory sessionFactory;

    static {
        // Inicializa a fábrica de sessões Hibernate usando arquivo de configuração
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    /**
     * Busca um administrador pelo valor da senha.
     * @param senha senha do administrador a ser buscado
     * @return true se o administrador for encontrado, false caso contrário
     */
    public boolean buscarPorSenha(String senha) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            List<AdministradorEntity> admins = session.createQuery(
                            "FROM AdministradorEntity WHERE senha = :senha", AdministradorEntity.class)
                    .setParameter("senha", senha)
                    .list();

            transaction.commit();

            return !admins.isEmpty();

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao buscar Administrador por senha", e);
        } finally {
            session.close();
        }
    }


    /**
     * Deleta um administrador baseado no CPF associado.
     * @param cpf CPF do administrador a ser removido
     */
    public void deletarCpfAdm(String cpf) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            AdministradorEntity adm = session
                    .createQuery("FROM AdministradorEntity c WHERE c.pessoaEntity.cpf = :cpf", AdministradorEntity.class)
                    .setParameter("cpf", cpf)
                    .uniqueResult();

            if (adm != null) {
                session.delete(adm);
                transaction.commit();
                JOptionPane.showMessageDialog(null, "Administrador removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Administrador não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    /**
     * Salva um novo administrador no banco.
     * @param administrador objeto AdministradorEntity a ser persistido
     */
    public void salvar(AdministradorEntity administrador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(administrador);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Lista todos os administradores cadastrados, carregando também dados da pessoa associada.
     * @return lista de administradores
     */
    public List<AdministradorEntity> listarTodosAdm() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            List<AdministradorEntity> adms = session
                    .createQuery("FROM AdministradorEntity a JOIN FETCH a.pessoaEntity", AdministradorEntity.class)
                    .getResultList();

            transaction.commit();
            return adms;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao listar administradores", e);
        } finally {
            session.close();
        }
    }

}
