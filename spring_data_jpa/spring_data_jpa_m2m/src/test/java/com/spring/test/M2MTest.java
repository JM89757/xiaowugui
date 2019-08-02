package com.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.RoleDao;
import com.spring.dao.UserDao;
import com.spring.domain.Role;
import com.spring.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class M2MTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;


    @Test
    @Transactional
    @Rollback(false)
    public void  testAdd() {
        User user = new User();
        user.setUserName("小王");

        Role role = new Role();
        role.setRoleName("屌丝");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void  testCascadeAdd() {
        User user = new User();
        user.setUserName("小李");

        Role role = new Role();
        role.setRoleName("经理");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void  testCascadeRemove() {
        User user = userDao.findOne(1L);
        userDao.delete(user);

    }

}
