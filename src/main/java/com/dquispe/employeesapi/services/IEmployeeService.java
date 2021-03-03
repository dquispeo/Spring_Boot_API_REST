package com.dquispe.employeesapi.services;

import java.util.List;

import com.dquispe.employeesapi.models.Employee;

public interface IEmployeeService {
	
public List<Employee> getAll();
public Employee save(Employee employee);
public Employee findById(Long id);
public void delete(Long id);

}
