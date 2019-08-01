package com.jpa.demo.main;

import com.jpa.demo.config.ApplicationConfig;
import com.jpa.demo.dao.AddressRepository;
import com.jpa.demo.dao.CustomerRepository;
import com.jpa.demo.dto.Address;
import com.jpa.demo.dto.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Import({ApplicationConfig.class})
public class DemoJpaApplication {

	static long customerId = 1l;

	public static void main(String[] args) {

		//test_1();
		test_2();


	}

	private static void test_2() {
		ApplicationContext context = SpringApplication.run(DemoJpaApplication.class);
		CustomerRepository customerRepository = context.getBean("jpaCustomerRepository", CustomerRepository.class);
		AddressRepository addressRepository = context.getBean("jpaAddressRepository", AddressRepository.class);

		//Customer customer = customerRepository.findCustomerById(1l);
		//System.out.println("Customer ="+customer);

//		if (customer != null) {
//			customerRepository.removeCustomer(customer);
//		}

//		customer = customerRepository.findCustomerById(2l);
//		System.out.println("Customer ="+customer);
//
//		if (customer != null) {
//			customerRepository.removeCustomer(customer);
//		}

		Customer customer1 = new Customer();
		customer1.setFirstName("Houssem");
		customer1.setLastName("KALLEL");
		customer1.setBirthDate(Date.from(LocalDate.of(1981, Month.NOVEMBER, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Address address1 = new Address();
		address1.setCity("Ariana");
		address1.setCountry("Tunisia");
		address1.setPostcode("2083");
		address1.setStreet("Route Raoued");
		addressRepository.addNewAddress(address1);

		Address address2 = new Address();
		address2.setCity("Ariana");
		address2.setCountry("Tunisia");
		address2.setPostcode("2073");
		address2.setStreet("Borj Louzir");
		addressRepository.addNewAddress(address2);

		Set<Address> addresses = new HashSet<>();
		addresses.add(address1);
		addresses.add(address2);
		customer1.setAddresses(addresses);

		customerRepository.addNewCustomer(customer1);


		Customer customer2 = new Customer();
		customer2.setFirstName("Walid");
		customer2.setLastName("KALLEL");
		customer2.setBirthDate(Date.from(LocalDate.of(1983, Month.MAY, 05).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		customerRepository.addNewCustomer(customer2);


		Customer cus = customerRepository.findCustomerById(1l);
		System.out.println("1) ====>"+cus);

		List<Customer> cc = customerRepository.findCustomerByCritera();
		System.out.println("2) ====>"+cc);
		if (!CollectionUtils.isEmpty(cc)) {
			cc.forEach(cuss -> {
				cuss.getAddresses().forEach(ad -> System.out.println("## Address = "+ad));
			});
		}

	}

	private static void test_1() {
		ApplicationContext context = SpringApplication.run(DemoJpaApplication.class);
		CustomerRepository customerRepository = context.getBean("jpaCustomerRepository", CustomerRepository.class);

//		Customer customer = new Customer();
//		customer.setId(customerId);
//		customer.setFirstName("Michel");
//		customer.setLastName("Platini");
//		LocalDate ld = LocalDate.of(1981, 11, 20);
//		Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		customer.setBirthDate(new Date(10,10,1990));
//		customerRepository.addNewCustomer(customer);

		Customer customer = customerRepository.findCustomerById(2l);
		System.out.println("Customer ="+customer);

		if (customer != null) {
			customerRepository.removeCustomer(customer);
		}
	}

}
