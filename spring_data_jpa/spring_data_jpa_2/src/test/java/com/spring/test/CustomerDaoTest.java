package com.spring.test;

import com.spring.dao.CustomerDao;
import com.spring.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;


    @Test
    public void findAll() {
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void findOne() {
        Customer customer = customerDao.findOne(2L);
        System.out.println(customer);
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setCustName("秦川冲压");
        customer.setCustIndustry("制造");
        customer.setCustAddress("陕西");
        customer.setCustLevel("VIP");
        customer.setCustPhone("09173958888");
        customer.setCustSource("内部");
        customerDao.save(customer);
    }

    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setCustId(7L);
        customer.setCustName("秦川格兰德");
        customer.setCustIndustry("制造");
        customer.setCustAddress("陕西");
        customer.setCustLevel("VIP");
        customer.setCustPhone("09173958888");
        customer.setCustSource("内部");
        customerDao.save(customer);
    }

    @Test
    public void delete() {
        customerDao.delete(1L);
    }

    @Test
    public void count() {
        long count = customerDao.count();
        System.out.println("总数是：" + count);
    }

    @Test
    public void exists() {
        boolean exists = customerDao.exists(1L);
        System.out.println("id为1的用户是否存在：" + exists);
    }

    @Test
    @Transactional
    public void getOne() {
        Customer customer = customerDao.getOne(2L);
        System.out.println(customer);
    }


}
