package com.jpa.demo.dao.impl;

import com.jpa.demo.dao.CustomerRepository;
import com.jpa.demo.dao.CustomerRepositoryCustom;
import com.jpa.demo.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class CustomerRepositoryImpl implements CustomerRepositoryCustom
{
	private static Logger LOGGER = LoggerFactory.getLogger(CustomerRepositoryCustom.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override public Customer findDeadbeatCustomers()
	{
		LOGGER.debug("Custom method findDeadbeatCustomers");
		return entityManager.find(Customer.class, 1l);
	}
}
