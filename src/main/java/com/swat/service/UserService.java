package com.swat.service;

import com.swat.dto.UserDTO;
import com.swat.dto.UserStatusUpdateDTO;
import com.swat.model.User;

import java.util.List;

public interface UserService {

	User getUserById(Integer id);

	User getUserByName();

	User createUser(UserDTO userDTO);

	User updateUser(UserDTO userDTO);

	UserStatusUpdateDTO changeUserStatus(Integer id, String statusCode);

	List<User> getUserList(String status);

}
