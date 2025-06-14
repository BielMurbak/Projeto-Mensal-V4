package org.grsstreet.repository;

import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.util.List;

public class AdministradorRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public boolean buscarPorSenha(String senha) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            AdministradorEntity admin = session
                    .createQuery("FROM AdministradorEntity WHERE senha = :senha", AdministradorEntity.class)
                    .setParameter("senha", senha)
                    .uniqueResult();

            transaction.commit();

            return admin != null;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao buscar Administrador por senha", e);
        } finally {
            session.close();
        }
    }

    public void deletarCpfAdm (String cpf){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

           AdministradorEntity adm = session
                   .createQuery( "FROM AdministradorEntity c " +  "WHERE c.pessoaEntity.cpf = :cpf", AdministradorEntity.class)
                    .setParameter("cpf", cpf)
                    .uniqueResult();

            if (adm != null) {
                session.delete(adm);
                transaction.commit();
                System.out.println("Adm deletado com sucesso.");
                JOptionPane.showMessageDialog(null, "adm removido com sucesso!");
            } else {
                System.out.println("Adm não encontrado.");
                JOptionPane.showMessageDialog(null, "Erro ao remover adm " ,"Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
    public void salvar(AdministradorEntity administrador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(administrador);
        session.getTransaction().commit();
        session.close();
    }

    public List<AdministradorEntity> listarTodosAdm() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            List<AdministradorEntity> adms= session
                    .createQuery("FROM AdministradorEntity a JOIN FETCH a.pessoaEntity", AdministradorEntity.class)
                    .getResultList();
            transaction.commit();

            return adms;

        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao listar adms", e);
        } finally {
            session.close();
        }
    }

}
