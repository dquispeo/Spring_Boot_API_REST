package com.dquispe.employeesapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dquispe.employeesapi.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
