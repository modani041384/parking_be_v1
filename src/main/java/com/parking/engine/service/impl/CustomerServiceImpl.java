package com.parking.engine.service.impl;

import com.parking.engine.entity.Customer;
import com.parking.engine.mapper.CustomerMapper;
import com.parking.engine.repository.CustomerRepository;
import com.parking.engine.request.CustomerDTO;
import com.parking.engine.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerMapper mapper;

    @Override
    public boolean create(CustomerDTO customerDTO) {
        try {
            Customer customer = mapper.convertDTOToEntity(customerDTO);
            repository.save(customer);
            return true;
        } catch (Exception ex) {
            log.error("Error when create customer: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<CustomerDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Customer> customers = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(customers);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all customers:", e);
        }
        return false;
    }

    @Override
    public boolean update(CustomerDTO customerDTO) {
        try {
            Customer customer = mapper.convertDTOToEntity(customerDTO);
            repository.save(customer);
            return true;
        } catch (Exception ex) {
            log.error("Error when update customer: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<CustomerDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Customer> customers = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(customers);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all customers:", e);
        }
        return false;
    }

    @Override
    public CustomerDTO findById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        return customer.isPresent() ? mapper.convertEntityToDTO(customer.get()) : new CustomerDTO();
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = repository.findAll();
        if (null != customers && 0 < customers.size()) {
            return customers.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<CustomerDTO> search(CustomerDTO filter) {
        return null;
    }

    //end
}
