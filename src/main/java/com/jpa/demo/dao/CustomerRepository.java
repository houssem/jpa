package com.jpa.demo.dao;

import com.jpa.demo.dto.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    public Customer findByFirstNameLike(String name);

    @Query("SELECT c from Customer c where c.email NOT LIKE '%@%' ")
    public List<Customer> findInvalidEMails();
}
