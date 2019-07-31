package com.spring.test;

import com.spring.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("all")
public class JpqlTest {

    @Test
    public void find() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        String jpql = "from Customer ";
        Query query = manager.createQuery(jpql);

        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }

        transaction.commit();
        manager.close();
    }


    @Test
    public void orderBy() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        String jpql = "from Customer order by custId desc ";
        Query query = manager.createQuery(jpql);

        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }

        transaction.commit();
        manager.close();

    }


    @Test
    public void count() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        String jpql = "select count(custId) from Customer ";
        Query query = manager.createQuery(jpql);

        Object result = query.getSingleResult();
        System.out.println("总数是：" + result);

        transaction.commit();
        manager.close();

    }


    @Test
    public void page() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        String jpql = "from Customer ";
        Query query = manager.createQuery(jpql);

        query.setFirstResult(0);
        query.setMaxResults(2);

        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }

        transaction.commit();
        manager.close();

    }


    @Test
    public void condition() {
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        String jpql = "from Customer where custName like ?";
        Query query = manager.createQuery(jpql);

        query.setParameter(1, "秦川%");

        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }

        transaction.commit();
        manager.close();

    }


}
