package com.centime.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centime.dto.CustomerDTO;
import com.centime.dto.FriendFamilyDTO;
import com.centime.entity.Customer;
import com.centime.service.CustomerService;

@RestController
@CrossOrigin
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired(required=true)
	CustomerService custService;

	@GetMapping(value = "/getCustomers")
	public List<Customer> getAllCustomers() {
		List<Customer> customers = custService.getAllCustomer();
		return customers;
	}	
	

	@PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody CustomerDTO custDTO) {
		logger.info("Creation request for customer {}", custDTO);
		custService.createCustomer(custDTO);
	}


	// Fetches full profile of a specific customer

	@GetMapping(value = "/customers/{phoneNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) {

		logger.info("Profile request for customer {}", phoneNo);
		return custService.getCustomerProfile(phoneNo);
	}	
	// Save the friend details of a specific customer

	@PostMapping(value = "/customers/{phoneNo}/friends", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
		custService.saveFriend(phoneNo, friendDTO);
	}

}
