package com.example.app.ws.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetailsRequestModel {
	@NotNull(message="First name cannot be null")
	@Size(min=2, message = "Valid name must be at least 2 characters long")
	private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min=2, message = "Valid name must be at least 2 characters long")
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
