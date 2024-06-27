package com.backend.tokokantjil.repositories;

import com.backend.tokokantjil.models.account.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
