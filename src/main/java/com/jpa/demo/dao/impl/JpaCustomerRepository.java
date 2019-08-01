package com.jpa.demo.dao.impl;

import com.jpa.demo.dao.CustomerRepository;
import com.jpa.demo.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    private static Logger LOGGER = LoggerFactory.getLogger(JpaCustomerRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer findCustomerById(Long id) {
        Customer cust = entityManager.find(Customer.class, id);
        // if (false)
        // throw new RuntimeException();
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
    public List<Customer> findCustomerByCriteria() {
        LOGGER.debug("find customer by criteria");
        List<Customer> customers = entityManager.createQuery("select DISTINCT c from Customer c JOIN FETCH c.addresses as a where a.city = :city", Customer.class).setParameter("city", "Ariana").getResultList();
        if (!CollectionUtils.isEmpty(customers)) {
            return customers;
        }
        return Collections.emptyList();
    }

}
