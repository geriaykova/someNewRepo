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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.app.ws.ui.model.request.UserDetailsRequestModel;
import com.example.app.ws.ui.model.response.UserRest;
import com.example.app.ws.userservice.IUserService;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	//dependency injection -> logic of createUser is separated from other logic
	@Autowired
	IUserService userService;

	Map<String, UserRest> users;

	/*
	 * @RequestParam() is used to make the query string request parameters
	 * available; now making http://localhost:8080/users?page=1&limit=50 valid
	 * pagination -> returns a page with a fixed max number of users per page by
	 * adding a default value for the page and limit parameters, we make the
	 * parameters optional another way to make a parameter optional is by adding
	 * required=false but it does't work very well with Primitives because they have
	 * to be initialized to be converted to null
	 */
	@GetMapping
	public Map getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		// if (sort == null) sort = "desc"; -> same as default value
		return users;
	}

	/*
	 * binds URL /users/userId -> userId is a pathParameter -> the request returns a
	 * single record with user details (Java Object as return value)
	 */
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		// String firstName = null;
		// int firstNameLenght = firstName.length(); // -> NullPointerException

//		if (true) {
//			throw new UserServiceException("A user service exception is thrown");
//		}


		if (userService.getUsers().containsKey(userId)) {
			return new ResponseEntity<>(userService.getUsers().get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		// set custom STATUS CODE
		// return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
	}

	// To validate we add @Valid and do the validation in UserDetailsRequestModel
	// using HibernateBeenValidationConstraints
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

		/*
		 * MOVED TO UserServiceImplementation
		UserRest returnValue = new UserRest();
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setEmail(userDetails.getEmail());
		// temporary store User Record so it can be updated
		// return "createUser was called"; -> does not accept any arguments

		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);

		if (users == null)
			users = new HashMap<>();
		
		users.put(userId, returnValue);
		*/
		
		
		UserRest returnValue = userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);

	}

	// to update User Record -> use Map to keep the information in the memory and
	// loose information once the Application is started
	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, path = "/{userId}")
	// adds method arguments
	public UserRest updateUser(@PathVariable String userId,
							   @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {

		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());

		users.put(userId, storedUserDetails);

		return storedUserDetails;
		// return "updateUser was called";
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {

		users.remove(id);
		return ResponseEntity.noContent().build();
		// return "deleteUser was called";
	}
}
