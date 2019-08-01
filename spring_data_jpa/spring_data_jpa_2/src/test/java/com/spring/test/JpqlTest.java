package com.spring.test;

import com.spring.dao.CustomerDao;
import com.spring.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void findJpql() {
        Customer customer = customerDao.findJpql("秦川机床");
        System.out.println(customer);
    }

    @Test
    public void findCustomerAndId() {
        Customer customer = customerDao.findCustomerAndId(3L, "秦川铸造");
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void updateCustomer() {
        customerDao.updateCustomer(4L, "秦川进阶");
    }

    @Test
    public void findSql() {
        List<Object[]> list = customerDao.findSql("秦川铸%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }

        List<Object[]> sql = customerDao.findSql();
        for (Object[] objects : sql) {
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void findByCustName() {
        Customer customer = customerDao.findByCustName("秦川铸造");
        System.out.println(customer);
    }


    @Test
    public void findByCustNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("秦川机床%");
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }

    @Test
    public void findByCustNameLikeAndCustAddress() {
        Customer customer = customerDao.findByCustNameLikeAndCustAddress("秦川机床2%", "陕西");
        System.out.println(customer);
    }


}
