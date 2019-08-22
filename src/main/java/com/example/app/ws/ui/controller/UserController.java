package com.example.app.ws.ui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import com.example.app.ws.userrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.app.ws.ui.model.response.UserRest;
import com.example.app.ws.userservice.IUserService;


@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	//Spring framework will create an instance of the UserServiceImpl
	// and will inject that instance into this UserController object
	@Autowired
	IUserService userService;
	@Autowired
	UserRepository userRepository;


	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers() {
		return userRepository.findAll();
	}

	/*
	 * binds URL /users/userId -> userId is a pathParameter -> the request returns a
	 * single record with user details (Java Object as return value)
	 */
	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserRest> getUserById(@PathVariable(value = "id") long userId) {

		Optional<UserRest> userRest = userRepository.findById(userId);
		return ResponseEntity.ok().body(userRest.get());

		//causing an exception
//		String firstName = null;
//		int firstNameLength = firstName.length();
//		if(true) {
//			throw new UserServiceException("A custom exc is thrown");
//		}
	}

	// To validate we add @Valid and do the validation in UserDetailsRequestModel
	// using HibernateBeenValidationConstraints
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@Valid @RequestBody UserRest userRest) {

		//autowiring a service layer class
		UserRest returnValue = userService.createUser(userRest);
		return userRepository.save(userRest);

//		creating direct dependency on the UserServiceImpl
//		 not being able to test createUser() independently
//		UserRest returnValue = new UserServiceImpl().createUser(userRest);


//
//		return new ResponseEntity<>(returnValue, HttpStatus.OK);
	}


	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE },
			path = "/{userId}")
	public ResponseEntity<UserRest> updateUser(@PathVariable(value = "id") long userId,
							   @Valid @RequestBody UserRest userDetails) {

		UserRest userRest = userRepository.findById(userId).get();

		userRest.setFirstName(userDetails.getFirstName());
		userRest.setLastName(userDetails.getLastName());
		userRest.setEmail(userDetails.getEmail());

		final UserRest updatedUser = userRepository.save(userRest);
		return ResponseEntity.ok(updatedUser);

	}

	@DeleteMapping(path = "/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") long userid) {

		UserRest userRest = userRepository.findById(userid).get();

		userRepository.delete(userRest);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}
}
