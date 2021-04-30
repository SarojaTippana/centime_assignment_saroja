package com.centime.entity;

import javax.validation.constraints.NotNull;

public class Person {
	@NotNull(message = "First Name cannot be empty")
	private String firstName;
	@NotNull (message = "Last Name cannot be empty")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
