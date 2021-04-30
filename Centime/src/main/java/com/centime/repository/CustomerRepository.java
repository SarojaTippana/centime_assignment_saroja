package com.centime.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.centime.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
}
