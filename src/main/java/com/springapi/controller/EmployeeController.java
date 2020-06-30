package com.springapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapi.dao.EmployeeDAO;
import com.springapi.model.Employee;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	/* to save an employee*/
	@PostMapping("/employees")
	@ApiOperation(value = "save employee",notes="emp save")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully passed")})
	public Employee createEmployee(@RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
	
	/*get all employees*/
	@GetMapping("/employees")
	@ApiOperation(value = "get all employees",notes="get all employees")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully passed")})
	public List<Employee> getAllEmployees(){
		return employeeDAO.findAll();
	}
	
	/*get employee by empid*/
	@GetMapping("/employees/{id}")
	@ApiOperation(value = "get employee by empid",notes="get employee by empid")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully passed")})
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){
		
		Optional<Employee> emp=employeeDAO.findOne(empid);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp.get());
		
	}
	
	
	/*update an employee by empid*/
	@PutMapping("/employees/{id}")
	@ApiOperation(value = "update an employee by empid",notes="update an employee by empid")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully passed")})
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empid,@RequestBody Employee empDetails){
		
		Optional<Employee> empOtional=employeeDAO.findOne(empid);
		Employee emp = empOtional.get();
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExpertise(empDetails.getExpertise());
		
		Employee updateEmployee=employeeDAO.save(emp);
		return ResponseEntity.ok().body(updateEmployee);
		
		
		
	}
	
	/*Delete an employee*/
	@DeleteMapping("/employees/{id}")
	@ApiOperation(value = "Delete an employee",notes="Delete an employee")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "successfully passed")})
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid){
		
		Optional<Employee> emp=employeeDAO.findOne(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		employeeDAO.delete(emp.get());
		
		return ResponseEntity.ok().build();
		
		
	}
	
	

}
