package com.example.app.ws.ui.model.request;

//import com.example.app.ws.ui.model.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "people")
public class UserRequestModel  {

	private long userId;

	@NotNull(message="First name cannot be null")
	@Size(min=2, max=40, message = "Valid name is between 2 and 40 characters")
	private String firstName;

	@NotNull(message="Last name cannot be null")
	@Size(min=2, max=40, message = "Valid name is between 2 and 40 characters")
	private String lastName;

	@NotNull(message="e-mail cannot be null")
	@Email
	private String email;

	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message="Password must be equal ot greater than 8 characters and less than 16 characters")
	private String password;

	public UserRequestModel(){}
	public UserRequestModel(long userId,
							String firstName,
							String lastName,
							String email,
							String password){
		this.userId = userId;
		this.firstName =  firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "firstname", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastname", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
