package com.dquispe.employeesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dquispe.employeesapi.models.Employee;
import com.dquispe.employeesapi.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAll() {
		return (List<Employee>)employeeRepository.findAll();
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee findById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

}
