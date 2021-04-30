package com.centime.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centime.entity.Employee;
import com.centime.repository.EmployeeRepository;

@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmployeeRepository empRepo;

	public void createEmployee(Employee emp) {
		empRepo.save(emp);
	}

	public ArrayList<Employee> getAllEmployee() {
		return (ArrayList<Employee>) empRepo.findAll();
	}

	public Optional<Employee> getEmployee(long id) {
		System.out.println(" service to get employeee");
		return empRepo.findById(id);
	}

}
