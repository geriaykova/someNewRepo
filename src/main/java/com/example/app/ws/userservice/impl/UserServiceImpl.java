package com.example.app.ws.userservice.impl;

//import com.example.app.ws.ui.model.User;
import com.example.app.ws.exceptions.UserServiceException;
import com.example.app.ws.ui.model.request.UserRequestModel;
import com.example.app.ws.ui.model.request.UserUpdateModel;
import com.example.app.ws.userrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.app.ws.ui.model.response.UserResponseModel;
import com.example.app.ws.userservice.IUserService;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl() {}

	@Override
	public UserResponseModel createUser(UserRequestModel userRequest) {
		userRepository.save(userRequest);

		return new UserResponseModel(
				userRequest.getUserId(),
				userRequest.getFirstName(),
				userRequest.getLastName(),
				userRequest.getEmail());

	}


	@Override
	public UserResponseModel getUserByID(long userId) {
		if(userRepository.findById(userId).isPresent()){
			UserRequestModel user = userRepository.findById(userId).get();
			return new UserResponseModel(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail());
		}
		else {
			throw new UserServiceException("There is no user with id: " + userId);
		}
	}

	@Override
	public List<UserResponseModel> getUsers() {
		if(!userRepository.findAll().isEmpty()) {
			List<UserResponseModel> users = new ArrayList<>();
			userRepository
					.findAll()
					.forEach(u -> users.add(new UserResponseModel(
							u.getUserId(),
							u.getFirstName(),
							u.getLastName(),
							u.getEmail()))
					);
			return users;
		}
		else {
			throw new UserServiceException("There are no users in the database");
		}
	}

	@Override
	public UserResponseModel updateUser(long userId, UserUpdateModel userUpdateModel) {
		if(userRepository.findById(userId).isPresent()){
			UserRequestModel user = userRepository.findById(userId).get();

			user.setFirstName(userUpdateModel.getFirstName());
			user.setLastName(userUpdateModel.getLastName());
			user.setEmail(userUpdateModel.getEmail());
			user.setPassword(userUpdateModel.getPassword());

			userRepository.save(user);

			return new UserResponseModel(user);
		}
		else{
			throw new UserServiceException("Unable to update user. There is no user with id: " + userId);
		}
	}

	@Override
	public UserResponseModel deleteUser(long userId) {
		if(userRepository.findById(userId).isPresent()){
			UserRequestModel deletedUser = userRepository.findById(userId).get();
			userRepository.delete(deletedUser);
			return new UserResponseModel(deletedUser);
		}
		else{
			throw new UserServiceException("Unable to delete user. There is no user with id: " + userId);
		}
	}

}
