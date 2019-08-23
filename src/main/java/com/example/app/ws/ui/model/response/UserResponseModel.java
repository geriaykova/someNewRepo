package com.example.app.ws.ui.model.response;

//import com.example.app.ws.ui.model.User;
import com.example.app.ws.ui.model.request.UserRequestModel;

public class UserResponseModel {

	private long userId;
	private String firstName;
	private String lastName;
	private String email;

	public UserResponseModel(){}
	public UserResponseModel(long userId, String firstName, String lastName, String email) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public UserResponseModel(UserRequestModel urm){
		this.userId = urm.getUserId();
		this.firstName = urm.getFirstName();
		this.lastName = urm.getLastName();
		this.email = urm.getEmail();
	}


}
