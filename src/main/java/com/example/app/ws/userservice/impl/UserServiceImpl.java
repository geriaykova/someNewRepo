package com.example.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.ws.shared.Utils;
import com.example.app.ws.ui.model.request.UserDetailsRequestModel;
import com.example.app.ws.ui.model.response.UserRest;
import com.example.app.ws.userservice.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	Map<String, UserRest> users;
	Utils utils;
	

	public Map<String, UserRest> getUsers() {
		return users;
	}

	public void setUsers(Map<String, UserRest> users) {
		this.users = users;
	}

	public UserServiceImpl() {};

	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();

		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setEmail(userDetails.getEmail());

		String userId = utils.generateUserId();
		returnValue.setUserId(userId);

		if (users == null)
			users = new HashMap<>();

		users.put(userId, returnValue);

		return returnValue;

	}

}
