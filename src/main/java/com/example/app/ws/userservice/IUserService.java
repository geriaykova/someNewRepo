package com.example.app.ws.userservice;

import java.util.Map;

import com.example.app.ws.ui.model.request.UserDetailsRequestModel;
import com.example.app.ws.ui.model.response.UserRest;

public interface IUserService {
	UserRest createUser(UserRest userRest);


}
