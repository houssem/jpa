package com.jpa.demo.dao;

import com.jpa.demo.dto.Address;
import org.springframework.data.repository.CrudRepository;


public interface AddressRepository extends CrudRepository<Address, Long>
{
    //
}
