package com.jpa.demo.main;

import com.jpa.demo.config.ApplicationConfig;
import com.jpa.demo.dao.AddressRepository;
import com.jpa.demo.dao.CustomerRepository;
import com.jpa.demo.dto.Address;
import com.jpa.demo.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;


@Import({ApplicationConfig.class})
public class DemoJpaApplication {

	public static Logger LOGGER = LoggerFactory.getLogger(DemoJpaApplication.class);

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(DemoJpaApplication.class);
		CustomerRepository customerRepository = context.getBean("customerRepository", CustomerRepository.class);
		AddressRepository addressRepository = context.getBean("addressRepository", AddressRepository.class);

		Customer customer1 = new Customer();
		customer1.setFirstName("Houssem");
		customer1.setLastName("KALLEL");
		customer1.setBirthDate(Date.from(LocalDate.of(1981, Month.NOVEMBER, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		customer1.setEmail("houssem.kallel@gmail.com");

		Address address1 = new Address();
		address1.setCity("Ariana");
		address1.setCountry("Tunisia");
		address1.setPostcode("2083");
		address1.setStreet("Route Raoued");
		addressRepository.save(address1);

		Address address2 = new Address();
		address2.setCity("Ariana");
		address2.setCountry("Tunisia");
		address2.setPostcode("2073");
		address2.setStreet("Borj Louzir");
		addressRepository.save(address2);

		Set<Address> addresses = new HashSet<>();
		addresses.add(address1);
		addresses.add(address2);
		customer1.setAddresses(addresses);

		customerRepository.save(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("Walid");
		customer2.setLastName("KALLEL");
		customer2.setBirthDate(Date.from(LocalDate.of(1983, Month.MAY, 05).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		customer2.setEmail("walid.kallelgmail.com");
		customerRepository.save(customer2);

		LOGGER.info("Test using find by id");
		Optional<Customer> result = customerRepository.findById(1l);
		if (result.isPresent()) {
			Customer customer = result.get();
			LOGGER.info("Customer result : {}", customer);
		}

//		Customer customerResult = customerRepository.getOne(1l);
//		LOGGER.info("Test getOne {}", customerResult);

		Customer customer11 = customerRepository.findDeadbeatCustomers();
		LOGGER.info("customer11 {}", customer11);

		Customer customer = customerRepository.findByFirstNameLike("houssem");
		LOGGER.info("Customer result : {}", customer);

		List<Customer> list = customerRepository.findInvalidEMails();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(x -> {
				LOGGER.info("Customer result : {}", x);
			});
		}

	}

}
