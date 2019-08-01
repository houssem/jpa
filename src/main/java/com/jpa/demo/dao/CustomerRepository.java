package com.jpa.demo.dao;

import com.jpa.demo.dto.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer findCustomerById(Long id);

    void addNewCustomer(Customer customer);

    void removeCustomer(Customer customer);

    List<Customer> findCustomerByCriteria();
}
