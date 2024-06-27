package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.EmployeeInputDto;
import com.backend.tokokantjil.dtos.outputs.EmployeeOutputDto;
import com.backend.tokokantjil.mappers.EmployeeMapper;
import com.backend.tokokantjil.models.account.Employee;
import com.backend.tokokantjil.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

//    public EmployeeOutputDto getEmployeeById(Long id) {
//        Employee e = this.employeeRepository.findById(id).orElseThrow() -> RecordNotFoundEx
//    }

    public EmployeeOutputDto createEmployee(EmployeeInputDto employeeInputDto) {
        Employee e = this.employeeRepository.save(EmployeeMapper.fromEmployeeInputDtoToEmployee(employeeInputDto));
        return EmployeeMapper.fromEmployeeToEmployeeOutputDto(e);
    }
}
