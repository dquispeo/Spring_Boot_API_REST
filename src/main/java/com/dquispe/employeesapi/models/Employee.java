package com.dquispe.employeesapi.models;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	@JsonProperty(
	        value="name",
	        required=true,
	        defaultValue="No name",
	        access= JsonProperty.Access.READ_WRITE)
	@Size(max=100)
	@Column(nullable = false)
	private String name;
	@JsonProperty(
	        value="lastname",
	        required=true,
	        defaultValue="No lastname",
	        access= JsonProperty.Access.READ_WRITE)
	@Size(max=100)
	@Column(nullable = false)
	private String lastname;
	@JsonProperty(
	        value="dni",
	        required=true,
	        defaultValue="00000000",
	        access= JsonProperty.Access.READ_WRITE)
	@Size(min=8, max=8)
	@Column(nullable = false)
	private String dni;
	@JsonProperty(
	        value="salary",
	        required=true,
	        defaultValue="0.00",
	        access= JsonProperty.Access.READ_WRITE)
	@Column(nullable = false)
	private Double salary;
	@JsonProperty(
	        value="position",
	        required=true,
	        defaultValue="No position",
	        access= JsonProperty.Access.READ_WRITE)
	@Size(max=100)
	@Column(nullable = false)
	private String position;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", lastname=" + lastname + ", dni=" + dni + ", salary="
				+ salary + ", position=" + position + "]";
	}
	
}
