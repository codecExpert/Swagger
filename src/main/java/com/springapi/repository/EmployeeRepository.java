package com.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapi.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}