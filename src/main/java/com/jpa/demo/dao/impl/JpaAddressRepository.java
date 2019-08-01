package com.jpa.demo.dao.impl;

import com.jpa.demo.dao.AddressRepository;
import com.jpa.demo.dto.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaAddressRepository implements AddressRepository {

    private static Logger LOGGER = LoggerFactory.getLogger(JpaAddressRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address findAddressById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void addNewAddress(Address address) {
        LOGGER.debug("Add new address {} ",address);
        entityManager.persist(address);
    }
}
