package com.example.app.ws.ui.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.app.ws.ui.model.request.UserDetailsRequestModel;
import com.example.app.ws.ui.model.response.UserRest;
import com.example.app.ws.userservice.IUserService;


@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	//Spring framework will create an instance of the UserServiceImpl
	// and will inject that instance into this UserController object
	@Autowired
	IUserService userService;


	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, UserRest> getUsers() {
		return userService.getUsers();
	}

	/*
	 * binds URL /users/userId -> userId is a pathParameter -> the request returns a
	 * single record with user details (Java Object as return value)
	 */
	@GetMapping(path = "/{userId}",
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

		//causing an exception
//		String firstName = null;
//		int firstNameLength = firstName.length();
//		if(true) {
//			throw new UserServiceException("A custom exc is thrown");
//		}

		if (userService.getUsers().containsKey(userId)) {
			return new ResponseEntity<>(userService.getUsers().get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	// To validate we add @Valid and do the validation in UserDetailsRequestModel
	// using HibernateBeenValidationConstraints
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

		//creating direct dependency on the UserServiceImpl
		// not being able to test createUser() independently
//		UserRest returnValue = new UserServiceImpl().createUser(userDetails);

		//autowiring a service layer class
		UserRest returnValue = userService.createUser(userDetails);

		return new ResponseEntity<>(returnValue, HttpStatus.OK);

	}


	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE },
			path = "/{userId}")
	public UserRest updateUser(@PathVariable String userId,
							   @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {

		UserRest storedUserDetails = userService.getUsers().get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());

		userService.getUsers().put(userId, storedUserDetails);

		return storedUserDetails;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {

		userService.getUsers().remove(id);

		return ResponseEntity.noContent().build();
	}
}
