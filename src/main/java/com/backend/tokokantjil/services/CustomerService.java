package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.CustomerInputDto;
import com.backend.tokokantjil.dtos.outputs.CustomerOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.dtos.mappers.CustomerMapper;
import com.backend.tokokantjil.models.users.Customer;
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
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No customer with id " + id + " found."));
        return CustomerMapper.fromCustomerToCustomerOutputDto(customer);
    }

    public CustomerOutputDto createCustomer(CustomerInputDto customerInputDto) {
        Customer customer = this.customerRepository.save(CustomerMapper.fromCustomerInputDtoToCustomer(customerInputDto));
        return CustomerMapper.fromCustomerToCustomerOutputDto(customer);
    }

    public void deleteCustomer(Long id) {
        if(this.customerRepository.findById(id).isPresent()) {
            this.customerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No customer with id " + id + " found.");
        }
    }

    public CustomerOutputDto updateCustomer(Long id, CustomerInputDto customerInputDto) {
        Customer oldCustomer = this.customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No customer with id " + id + " found."));
        Customer customerUpdate = CustomerMapper.fromCustomerInputDtoToCustomer(customerInputDto);
        Customer newCustomer = this.customerRepository.save(CustomerMapper.fromCustomerToUpdatedCustomer(oldCustomer, customerUpdate));

        return CustomerMapper.fromCustomerToCustomerOutputDto(newCustomer);
    }
}
