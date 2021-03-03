package com.dquispe.employeesapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dquispe.employeesapi.models.Employee;
import com.dquispe.employeesapi.services.IEmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	IEmployeeService iEmployeeService;
	
	@GetMapping("/all")
	public ResponseEntity<?> allEmployee(){
		List<Employee> list = iEmployeeService.getAll();
		if(list != null) {
			if(list.size() != 0) {
				return new ResponseEntity<>(list,HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
		Employee employee2 = null;
		Map<String, Object> response = new HashMap<>();
		try {
			employee2 = iEmployeeService.save(employee);
		}catch(DataAccessException e) {
			response.put("message", "Employee not created");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Employee created successfully");
		response.put("employee", employee2);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> byIdEmployee(@PathVariable("id") Long id){
		Employee employee = null;
		Map<String, Object> response = new HashMap<>();
		try {
			employee = iEmployeeService.findById(id);
		}catch (DataAccessException e) {
			response.put("message", "Employee not found");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(employee==null) {
			response.put("message", "The employee with the ID: ".concat(id.toString()).concat(" does not exist."));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmpoyee(@RequestBody Employee employee, @PathVariable("id") Long id){
		Employee employeeById = iEmployeeService.findById(id);
		Employee employee2 = null;
		Map<String, Object> response = new HashMap<>();
		if(employeeById==null) {
			response.put("message", "The employee with the ID: ".concat(id.toString()).concat(" does not exist."));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			employeeById.setName(employee.getName());
			employeeById.setLastname(employee.getLastname());
			employeeById.setDni(employee.getDni());
			employeeById.setSalary(employee.getSalary());
			employeeById.setPosition(employee.getPosition());
			employee2 = iEmployeeService.save(employeeById);
		}catch(DataAccessException e) {
			response.put("message", "Could not update employee");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Employee updated successfully");
		response.put("employee", employee2);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			iEmployeeService.delete(id);
		}catch(DataAccessException e) {
			response.put("message", "Could not delete employee");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Employee removed successfully");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
