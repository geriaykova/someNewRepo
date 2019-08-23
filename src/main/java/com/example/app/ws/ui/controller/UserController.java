package com.example.app.ws.ui.controller;

import java.util.List;
import javax.validation.Valid;
import com.example.app.ws.ui.model.request.UserRequestModel;
import com.example.app.ws.ui.model.request.UserUpdateModel;
import com.example.app.ws.userrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.app.ws.ui.model.response.UserResponseModel;
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
	public ResponseEntity<List<UserResponseModel>> getUsers() {

		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);

	}


	/*
	 * binds URL /users/userId -> userId is a pathParameter -> the request returns a
	 * single record with user details (Java Object as return value)
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseModel> getUserById(@PathVariable("id") long userId) {

		return new ResponseEntity<>(userService.getUserByID(userId), HttpStatus.OK);

	}


	// To validate we add @Valid and do the validation in UserRequestModel
	// using HibernateBeenValidationConstraints
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRequestModel> createUser(@Valid @RequestBody UserRequestModel userRequest) {

		userService.createUser(userRequest);
		return new ResponseEntity<>(userRequest, HttpStatus.OK);

	}


	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE },
			path = "/{id}")
	public ResponseEntity<UserResponseModel> updateUser(@PathVariable(value = "id") long userId,
														@Valid @RequestBody UserUpdateModel userUpdateModel) {

		return new ResponseEntity<>(userService.updateUser(userId, userUpdateModel), HttpStatus.OK);

	}


	@DeleteMapping(path = "/{id}")
	public ResponseEntity<UserResponseModel> deleteUser(@PathVariable(value = "id") long userId) {

		return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);

	}

}
