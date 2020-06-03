package com.codegym.repositories;

import com.codegym.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class Repositories implements IRepositories {

    SessionFactory sessionFactory;
    EntityManager entityManager;

    public Repositories(){
        this.sessionFactory = new Configuration().configure("hibernate.config.xml").buildSessionFactory();
        this.entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<Customer> getAllCustomer() {
        String query = "SELECT c FROM Customer c";
        return this.entityManager.createQuery(query, Customer.class).getResultList();
    }

    @Override
    public void insertCustomer(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Customer origin = new Customer();
            origin.setId(customer.getId());
            origin.setName(customer.getName());
            origin.setAge(customer.getAge());
            origin.setEmail(customer.getEmail());
            origin.setAddress(customer.getAddress());
            session.save(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
