package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.EmployeeInputDto;
import com.backend.tokokantjil.dtos.outputs.EmployeeOutputDto;
import com.backend.tokokantjil.models.user.Employee;

public class EmployeeMapper {

    public static Employee fromEmployeeInputDtoToEmployee(EmployeeInputDto employeeInputDto) {
        Employee employee = new Employee();

        employee.setFirstName(employeeInputDto.firstName);
        employee.setLastName(employeeInputDto.lastName);
        employee.setUsername(employeeInputDto.username);
        employee.setPassword(employeeInputDto.password);
        employee.setEmail(employeeInputDto.email);
        employee.setPhoneNumber(employeeInputDto.phoneNumber);
        employee.setNotes(employeeInputDto.notes);
        employee.setRole(employeeInputDto.role);
        employee.setSalary(employeeInputDto.salary);

        return employee;
    }

    public static EmployeeOutputDto fromEmployeeToEmployeeOutputDto(Employee employee) {
        EmployeeOutputDto employeeOutputDto = new EmployeeOutputDto();

        employeeOutputDto.setId(employee.getId());
        employeeOutputDto.setFirstName(employee.getFirstName());
        employeeOutputDto.setLastName(employee.getLastName());
        employeeOutputDto.setUsername(employee.getUsername());
        employeeOutputDto.setPassword(employee.getPassword());
        employeeOutputDto.setEmail(employee.getEmail());
        employeeOutputDto.setPhoneNumber(employee.getPhoneNumber());
        employeeOutputDto.setNotes(employee.getNotes());
        employeeOutputDto.setRole(employee.getRole());
        employeeOutputDto.setSalary(employee.getSalary());

        return employeeOutputDto;
    }

    public static Employee fromEmployeeToUpdatedEmployee(Employee employee, Employee employeeUpdate) {

        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setUsername(employeeUpdate.getUsername());
        employee.setPassword(employeeUpdate.getPassword());
        employee.setEmail(employeeUpdate.getEmail());
        employee.setPhoneNumber(employeeUpdate.getPhoneNumber());
        employee.setNotes(employeeUpdate.getNotes());
        employee.setRole(employeeUpdate.getRole());
        employee.setSalary(employeeUpdate.getSalary());

        return employee;
    }
}
