package com.centime.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.centime.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
}
