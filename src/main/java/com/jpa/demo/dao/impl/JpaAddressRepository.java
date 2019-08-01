package com.jpa.demo.dao.impl;

import com.jpa.demo.dao.AddressRepository;
import com.jpa.demo.dto.Address;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class JpaAddressRepository implements AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address findAddressById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void addNewAddress(Address address) {
        entityManager.persist(address);
    }
}
