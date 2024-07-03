package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.EmployeeInputDto;
import com.backend.tokokantjil.dtos.outputs.EmployeeOutputDto;
import com.backend.tokokantjil.models.account.Employee;

public class EmployeeMapper {

    public static Employee fromEmployeeInputDtoToEmployee(EmployeeInputDto employeeInputDto) {
        Employee e = new Employee();

        e.setFirstName(employeeInputDto.firstName);
        e.setLastName(employeeInputDto.lastName);
        e.setUsername(employeeInputDto.username);
        e.setPassword(employeeInputDto.password);
        e.setEmail(employeeInputDto.email);
        e.setPhoneNumber(employeeInputDto.phoneNumber);
        e.setNotes(employeeInputDto.notes);
        e.setRole(employeeInputDto.role);
        e.setSalary(employeeInputDto.salary);

        return e;
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

    public static Employee fromEmployeeToUpdatedEmployee(Employee e, Employee eUpdate) {

        e.setFirstName(eUpdate.getFirstName());
        e.setLastName(eUpdate.getLastName());
        e.setUsername(eUpdate.getUsername());
        e.setPassword(eUpdate.getPassword());
        e.setEmail(eUpdate.getEmail());
        e.setPhoneNumber(eUpdate.getPhoneNumber());
        e.setNotes(eUpdate.getNotes());
        e.setRole(eUpdate.getRole());
        e.setSalary(eUpdate.getSalary());

        return e;
    }
}
