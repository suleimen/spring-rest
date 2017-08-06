package com.swat.dao;

import com.swat.model.Status;
import com.swat.model.User;

import java.util.List;


public interface UserDao {

	User getUserById(int id);

	User getUserByName();

	User saveUser(User user);

	void updateUser(User user);

	List<User> getUserList(Status status);

}
