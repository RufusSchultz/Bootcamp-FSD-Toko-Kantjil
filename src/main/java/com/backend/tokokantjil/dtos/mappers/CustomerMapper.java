package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.CustomerInputDto;
import com.backend.tokokantjil.dtos.outputs.CustomerOutputDto;
import com.backend.tokokantjil.models.account.Customer;

public class CustomerMapper {
    public static Customer fromCustomerInputDtoToCustomer(CustomerInputDto customerInputDto) {
        Customer e = new Customer();

        e.setFirstName(customerInputDto.firstName);
        e.setLastName(customerInputDto.lastName);
        e.setUsername(customerInputDto.username);
        e.setPassword(customerInputDto.password);
        e.setEmail(customerInputDto.email);
        e.setPhoneNumber(customerInputDto.phoneNumber);
        e.setNotes(customerInputDto.notes);

        return e;
    }

    public static CustomerOutputDto fromCustomerToCustomerOutputDto(Customer customer) {
        CustomerOutputDto customerOutputDto = new CustomerOutputDto();

        customerOutputDto.setId(customer.getId());
        customerOutputDto.setFirstName(customer.getFirstName());
        customerOutputDto.setLastName(customer.getLastName());
        customerOutputDto.setUsername(customer.getUsername());
        customerOutputDto.setPassword(customer.getPassword());
        customerOutputDto.setEmail(customer.getEmail());
        customerOutputDto.setPhoneNumber(customer.getPhoneNumber());
        customerOutputDto.setNotes(customer.getNotes());

        return customerOutputDto;
    }

    public static Customer fromCustomerToUpdatedCustomer(Customer c, Customer cUpdate) {

        c.setFirstName(cUpdate.getFirstName());
        c.setLastName(cUpdate.getLastName());
        c.setUsername(cUpdate.getUsername());
        c.setPassword(cUpdate.getPassword());
        c.setEmail(cUpdate.getEmail());
        c.setPhoneNumber(cUpdate.getPhoneNumber());
        c.setNotes(cUpdate.getNotes());

        return c;
    }
}
