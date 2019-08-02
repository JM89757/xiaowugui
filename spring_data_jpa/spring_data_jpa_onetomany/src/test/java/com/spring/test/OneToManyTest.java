package com.spring.test;

import com.spring.dao.CustomerDao;
import com.spring.dao.LinkManDao;
import com.spring.domain.Customer;
import com.spring.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(false)
    public void add() {
        Customer customer = new Customer();
        customer.setCustName("红米");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小雷");

        customer.getLinkManSet().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void add1() {
        Customer customer = new Customer();
        customer.setCustName("小米");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小雷");

        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);

    }


    @Test
    @Transactional
    @Rollback(false)
    public void add2() {
        Customer customer = new Customer();
        customer.setCustName("红米");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小雷");

        linkMan.setCustomer(customer);
        customer.getLinkManSet().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);

    }


    @Test
    @Transactional
    @Rollback(false)
    public void cascadeAdd() {
        Customer customer = new Customer();
        customer.setCustName("老米");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("老雷");

        linkMan.setCustomer(customer);
        customer.getLinkManSet().add(linkMan);

        customerDao.save(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void cascadeDelete() {
        Customer customer = customerDao.findOne(1L);
        customerDao.delete(customer);

    }


}
