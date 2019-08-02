package com.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.spring.domain.User;

public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
}
