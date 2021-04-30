package com.centime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centime.dto.CustomerDTO;
import com.centime.dto.FriendFamilyDTO;
import com.centime.entity.Customer;
import com.centime.entity.Employee;
import com.centime.entity.FriendFamily;
import com.centime.entity.Plan;
import com.centime.repository.CustomerRepository;
import com.centime.repository.EmployeeRepository;

@Service
public class CustomerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerRepository custRepo;
	@Autowired
	EmployeeRepository empRepo;

	public void createEmployee() {
		Employee emp= new Employee();
		emp.setId(Long.valueOf(4));
		emp.setColour("blue");
		emp.setName("saroja");
		emp.setParent_id(2);
		empRepo.save(emp);
	}

	public List<Customer> getAllCustomer() {
		return custRepo.findAll();

	}

	public void createCustomer1(CustomerDTO custDTO) {
		Customer cust = custDTO.createEntity();

		custRepo.save(cust);
	}

	// Create a new customer
	public void createCustomer(CustomerDTO custDTO) {
		logger.info("Creation request for customer {}", custDTO);
		Customer cust = custDTO.createEntity();
		Plan p = new Plan();
		p.setPlanId(custDTO.getCurrentPlan().getPlanId());
		cust.setPlan(p);
		custRepo.save(cust);
	}

	// Fetches full profile of a specific customer
	public CustomerDTO getCustomerProfile(Long phoneNo) {
		CustomerDTO custDTO = null;
		logger.info("Profile request for customer {}", phoneNo);
		Optional<Customer> cust = custRepo.findById(phoneNo);
		if (cust.isPresent())
			custDTO = CustomerDTO.valueOf(cust.get());

		logger.info("Profile for customer : {}", custDTO);
		return custDTO;
	}

	// Save the friend details of a specific customer
	public void saveFriend(Long phoneNo, FriendFamilyDTO friendDTO) {
		logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
		friendDTO.setPhoneNo(phoneNo);
		FriendFamily friend = friendDTO.createFriend();
		List<FriendFamily> friendList = new ArrayList<>();
		friendList.add(friend);

		Customer c = custRepo.getOne(phoneNo);
		c.getFriends().add(friend);
		c.setFriends(c.getFriends());
		custRepo.save(c);
	}

}
