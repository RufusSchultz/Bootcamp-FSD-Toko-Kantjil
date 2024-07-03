package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.CustomerInputDto;
import com.backend.tokokantjil.dtos.outputs.CustomerOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.dtos.mappers.CustomerMapper;
import com.backend.tokokantjil.models.account.Customer;
import com.backend.tokokantjil.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerOutputDto> getAllCustomers() {
        List<CustomerOutputDto> list = new ArrayList<>();
        for (Customer i: this.customerRepository.findAll()) {
            list.add(CustomerMapper.fromCustomerToCustomerOutputDto(i));
        }
        return list;
    }

    public CustomerOutputDto getCustomerById(Long id) {
        Customer e = this.customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No customer with id " + id + " found."));
        return CustomerMapper.fromCustomerToCustomerOutputDto(e);
    }

    public CustomerOutputDto createCustomer(CustomerInputDto customerInputDto) {
        Customer e = this.customerRepository.save(CustomerMapper.fromCustomerInputDtoToCustomer(customerInputDto));
        return CustomerMapper.fromCustomerToCustomerOutputDto(e);
    }

    public void deleteCustomer(Long id) {
        if(this.customerRepository.findById(id).isPresent()) {
            this.customerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No customer with id " + id + " found.");
        }
    }

    public CustomerOutputDto updateCustomer(Long id, CustomerInputDto customerInputDto) {
        Customer c1 = this.customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No customer with id " + id + " found."));
        Customer c2 = CustomerMapper.fromCustomerInputDtoToCustomer(customerInputDto);
        Customer c3 = this.customerRepository.save(CustomerMapper.fromCustomerToUpdatedCustomer(c1, c2));

        return CustomerMapper.fromCustomerToCustomerOutputDto(c3);
    }
}
