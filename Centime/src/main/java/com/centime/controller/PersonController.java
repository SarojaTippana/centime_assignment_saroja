package com.centime.controller;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.centime.entity.Person;

@RestController
public class PersonController {

	@Autowired
	RestTemplate restTemplate;

//	@GetMapping(value = "/service1")
//	public ResponseEntity<String> service1() {
//		return new ResponseEntity<String>("UP", HttpStatus.OK);
//	}

	@GetMapping(value = "/service2")
	public ResponseEntity<String> service2() {
		return ResponseEntity.accepted().body("hello");
	}

	@PostMapping(value = "/service")
	public String service(@Valid @RequestBody Person person) {
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8082/service1/";
		String result = restTemplate.getForObject(url, String.class);
		String url1 = "http://localhost:8081/service3/";
		String url2 = "http://localhost:8081/service2/";
		Person pers = person;
		HttpEntity<Person> request = new HttpEntity<>(pers);
		String result2 = restTemplate.postForObject(url1, request, String.class);
		String result1 = restTemplate.getForObject(url2, String.class);
		return result1 + "...." + result2;
	}

	@PostMapping(value = "/service3")
	public String service3(@RequestBody Person per) {
		return per.getFirstName() + per.getLastName();

	}
}
