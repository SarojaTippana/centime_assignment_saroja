package com.centime.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centime.entity.Employee;
import com.centime.exception.InvalidInputException;
import com.centime.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	EmployeeService empService;

	@PostMapping(value = "/createEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp) {

		System.out.println(emp.getName() + emp.getId());
		if (emp instanceof Employee) {
			empService.createEmployee(emp);
			return new ResponseEntity<>("Sucessfully created an employee", HttpStatus.OK);
		} else
			throw new InvalidInputException("The input is not a valid employee object!!!!");
	}

	@GetMapping(value = "/getEmployee/{id}")
	public Optional<Employee> getEmployee(@PathVariable Long id) {
		Optional<Employee> emp = empService.getEmployee(id);
		return emp;
	}

	@GetMapping(value = "/getAllEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Employee> getEmployee() {
		ArrayList<Employee> emp = empService.getAllEmployee();
		return emp;
	}

	@GetMapping(value = "/dummy", produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, HashSet<String>> getEmployeedd() {
		ArrayList<Employee> emp = empService.getAllEmployee();
		ArrayList<Employee> emp1 = emp;
		HashMap<String, HashSet<String>> map = new HashMap();
		for (Employee employee : emp) {
			Long id = employee.getId();
			String result = "";
			HashSet<String> l = new HashSet<>();
			for (Employee employee1 : emp1) {
				if (employee1.getParent_id() == id) {
					l.add(employee1.getName());
					result = ":  " + employee1.getName();
					System.out.println(employee.getName() + result);
					if (map.containsKey(employee.getName())) {
						HashSet<String> a = map.get(employee.getName());
						a.add(employee1.getName());
					} else
						map.put(employee.getName(), l);

				}

			}
		}
		return map;
	}
}
