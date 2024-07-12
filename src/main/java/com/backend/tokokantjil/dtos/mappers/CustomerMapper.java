package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.CustomerInputDto;
import com.backend.tokokantjil.dtos.outputs.CustomerOutputDto;
import com.backend.tokokantjil.models.Customer;

public class CustomerMapper {
    public static Customer fromCustomerInputDtoToCustomer(CustomerInputDto customerInputDto) {
        Customer customer = new Customer();

        customer.setFirstName(customerInputDto.firstName);
        customer.setLastName(customerInputDto.lastName);
        customer.setEmail(customerInputDto.email);
        customer.setPhoneNumber(customerInputDto.phoneNumber);
        customer.setNotes(customerInputDto.notes);

        return customer;
    }

    public static CustomerOutputDto fromCustomerToCustomerOutputDto(Customer customer) {
        CustomerOutputDto customerOutputDto = new CustomerOutputDto();

        customerOutputDto.setId(customer.getId());
        customerOutputDto.setFirstName(customer.getFirstName());
        customerOutputDto.setLastName(customer.getLastName());
        customerOutputDto.setEmail(customer.getEmail());
        customerOutputDto.setPhoneNumber(customer.getPhoneNumber());
        customerOutputDto.setNotes(customer.getNotes());

        return customerOutputDto;
    }

    public static Customer fromCustomerToUpdatedCustomer(Customer customer, Customer customerUpdate) {

        customer.setFirstName(customerUpdate.getFirstName());
        customer.setLastName(customerUpdate.getLastName());
        customer.setEmail(customerUpdate.getEmail());
        customer.setPhoneNumber(customerUpdate.getPhoneNumber());
        customer.setNotes(customerUpdate.getNotes());

        return customer;
    }
}
