package com.jpa.demo.dao;

import com.jpa.demo.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom
{
    public Customer findByFirstNameLike(String name);

    @Query("SELECT c from Customer c where c.email NOT LIKE '%@%' ")
    List<Customer> findInvalidEMails();
}
