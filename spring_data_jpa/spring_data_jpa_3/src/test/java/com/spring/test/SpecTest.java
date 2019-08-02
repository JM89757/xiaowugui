package com.spring.test;

import com.spring.dao.CustomerDao;
import com.spring.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void test1() {

       /* Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custId = root.get("custName");
                Predicate predicate = cb.equal(custId, "秦川机床");
                return predicate;
            }
        };*/

        Specification<Customer> spec = (root, query, cb) -> {
            Path<Object> custName = root.get("custName");
            return cb.equal(custName, "秦川机床");
        };

        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    @Test
    public void test2() {
        /*Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custAddress = root.get("custAddress");

                Predicate p1 = cb.equal(custName, "秦川铸造");
                Predicate p2 = cb.equal(custAddress, "陕西");

                Predicate predicate = cb.and(p1, p2);
                return predicate;
            }
        };*/

        Specification<Customer> spec = (root, query, cb) -> {
            Path<Object> custName = root.get("custName");
            Path<Object> custAddress = root.get("custAddress");

            Predicate p1 = cb.equal(custName, "秦川铸造");
            Predicate p2 = cb.equal(custAddress, "陕西");

            return cb.and(p1, p2);
//            return cb.or(p1, p2);
        };

//        List<Customer> customer = customerDao.findAll(spec);
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);

    }

    @Test
    public void test3() {
  /*      Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "秦川机床%");
                return predicate;
            }
        };*/

        Specification<Customer> spec = (root, query, cb) -> {
            Path<Object> custName = root.get("custName");
            return cb.like(custName.as(String.class), "秦川机床%");
        };

        List<Customer> customerList = customerDao.findAll(spec);
        for (Customer customer : customerList) {
            System.out.println(customer);
        }

        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> customers = customerDao.findAll(spec, sort);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void test4() {
//        Specification<Customer> spec = null;

        Pageable pageable = new PageRequest(0, 2);
//        Page<Customer> page = customerDao.findAll(null, pageable);
        Page<Customer> page = customerDao.findAll(pageable);
        System.out.println(page.getContent());
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());

    }


}
