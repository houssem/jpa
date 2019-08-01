package com.jpa.demo.dao;

import com.jpa.demo.dto.Address;
import com.jpa.demo.dto.Customer;

public interface AddressRepository {

    Address findAddressById(Long id);
    void addNewAddress(Address address);
}
