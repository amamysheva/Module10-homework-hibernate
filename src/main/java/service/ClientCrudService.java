package service;

import entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService {
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    public void create(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public Client getById(long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Client.class, id);
        } finally {
            session.close();
        }
    }

    public void update(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Client client = session.get(Client.class, id);
            session.delete(client);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public List<Client> getAllClients() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Client", Client.class).list();
        } finally {
            session.close();
        }
    }
}
