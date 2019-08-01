package com.jpa.demo.dao.impl;

import com.jpa.demo.dao.CustomerRepository;
import com.jpa.demo.dto.Customer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

public class JpaCustomerRepository implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer findCustomerById(Long id) {
        Customer cust = entityManager.find(Customer.class, id);
        if (true)
        throw new RuntimeException();
        return cust;
    }

    @Override
    @Transactional
    public void addNewCustomer(Customer customer) {
        entityManager.persist(customer);
//        throw new RuntimeException();
    }

    @Override
    @Transactional
    public void removeCustomer(Customer customer) {
        Customer c = entityManager.merge(customer);
        entityManager.remove(c);
    }

    @Override
    public List<Customer> findCustomerByCritera() {
        List<Customer> customers = entityManager.createQuery("select DISTINCT c from Customer c JOIN FETCH c.addresses as a where a.city = :city", Customer.class).setParameter("city", "Ariana").getResultList();
        if (!CollectionUtils.isEmpty(customers)) {
            return customers;
        }
        return Collections.emptyList();
    }

}
