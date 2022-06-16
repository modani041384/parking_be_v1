package com.parking.engine.mapper;

import com.parking.engine.entity.Customer;
import com.parking.engine.request.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper extends AbstractMapper<Customer, CustomerDTO> {
    public CustomerMapper() {
        super(Customer.class, CustomerDTO.class);
    }
}
