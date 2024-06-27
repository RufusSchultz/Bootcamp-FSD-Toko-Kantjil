package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.EmployeeInputDto;
import com.backend.tokokantjil.dtos.outputs.EmployeeOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.mappers.EmployeeMapper;
import com.backend.tokokantjil.models.account.Employee;
import com.backend.tokokantjil.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeOutputDto> getAllEmployees() {
        List<EmployeeOutputDto> list = new ArrayList<>();
        for (Employee i: this.employeeRepository.findAll()) {
            list.add(EmployeeMapper.fromEmployeeToEmployeeOutputDto(i));
        }
        return list;
    }

    public EmployeeOutputDto getEmployeeById(Long id) {
        Employee e = this.employeeRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No employee with id " + id + " found."));
        return EmployeeMapper.fromEmployeeToEmployeeOutputDto(e);
    }

    public EmployeeOutputDto createEmployee(EmployeeInputDto employeeInputDto) {
        Employee e = this.employeeRepository.save(EmployeeMapper.fromEmployeeInputDtoToEmployee(employeeInputDto));
        return EmployeeMapper.fromEmployeeToEmployeeOutputDto(e);
    }

    public void deleteEmployee(Long id) {
        if(this.employeeRepository.findById(id).isPresent()) {
            this.employeeRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee with id " + id + " found.");
        }
    }

    public EmployeeOutputDto updateEmployee(Long id, EmployeeInputDto employeeInputDto) {
        Employee e1 = this.employeeRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No employee with id " + id + " found."));
        Employee e2 = EmployeeMapper.fromEmployeeInputDtoToEmployee(employeeInputDto);
        Employee e3 = this.employeeRepository.save(EmployeeMapper.fromEmployeeToUpdatedEmployee(e1, e2));

        return EmployeeMapper.fromEmployeeToEmployeeOutputDto(e3);
    }
}
