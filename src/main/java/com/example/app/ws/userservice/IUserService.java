package com.example.app.ws.userservice;

import com.example.app.ws.ui.model.request.UserRequestModel;
import com.example.app.ws.ui.model.request.UserUpdateModel;
import com.example.app.ws.ui.model.response.UserResponseModel;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IUserService {
	List<UserResponseModel> getUsers();
	UserResponseModel getUserByID(long userId);
	UserResponseModel createUser(UserRequestModel userRequest);
	UserResponseModel updateUser(long userId, UserUpdateModel userUpdateModel);
	UserResponseModel deleteUser(long userId);


}
