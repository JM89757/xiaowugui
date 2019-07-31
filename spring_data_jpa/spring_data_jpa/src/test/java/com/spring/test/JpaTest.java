package com.spring.test;

import com.spring.domain.Customer;
import com.spring.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    @Test
    public void save() {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//        EntityManager manager = factory.createEntityManager();
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setCustName("秦川机床");
        customer.setCustIndustry("制造");
        customer.setCustAddress("陕西");
        customer.setCustLevel("VIP");
        customer.setCustPhone("09173958888");
        customer.setCustSource("内部");

        manager.persist(customer);
        transaction.commit();
        manager.close();
//        factory.close();
    }


    @Test
    public void find() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = manager.find(Customer.class, 1L);
        System.out.println(customer);
        transaction.commit();
        manager.close();
    }

    @Test
    public void getReference() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = manager.getReference(Customer.class, 1L);
        System.out.println(customer);
        transaction.commit();
        manager.close();
    }

    @Test
    public void update() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = manager.getReference(Customer.class, 1L);
        customer.setCustSource("国内著名企业");
        manager.merge(customer);
        transaction.commit();
        manager.close();
    }


    @Test
    public void remove() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Customer customer = manager.find(Customer.class, 1L);
        manager.remove(customer);
        transaction.commit();
        manager.close();
    }

}
