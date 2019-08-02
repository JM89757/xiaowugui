package com.spring.dao;

import com.spring.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "from Customer where custName = ?1")
    public Customer findJpql(String custName);

    @Query(value = "from  Customer where custName = ?2 and custId = ?1")
    public Customer findCustomerAndId(Long id, String custName);

    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    public void updateCustomer(Long custId, String custName);


    @Query(value = "select * from cst_customer where cust_name  like ?1", nativeQuery = true)
    public List<Object[]> findSql(String name);

    @Query(value = " select * from cst_customer", nativeQuery = true)
    public List<Object[]> findSql();


    public Customer findByCustName(String custName);

    public List<Customer> findByCustNameLike(String custName);

    public Customer findByCustNameLikeAndCustAddress(String custName, String custAddress);





}
