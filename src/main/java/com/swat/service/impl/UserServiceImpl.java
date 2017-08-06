package com.swat.service.impl;

import com.swat.dao.UserDao;
import com.swat.dto.UserDTO;
import com.swat.dto.UserStatusUpdateDTO;
import com.swat.model.Status;
import com.swat.model.User;
import com.swat.service.UserService;
import com.swat.util.Precondition;
import com.swat.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User getUserById(Integer id) {

		Precondition.checkNotNull(id, "id is empty");

		User user = userDao.getUserById(id);

		Precondition.checkNotNull(user, "user with id: " + id + " not found");

		return user;
	}

	public User getUserByName() {
		return userDao.getUserByName();
	}

	public User createUser(UserDTO userDTO) {

		Precondition.checkNotNull(userDTO, "user is empty");
		User user = new User(userDTO);
		userDao.saveUser(user);

		return user;

	}

	public User updateUser(UserDTO userDTO) {

		User user = this.getUserById(userDTO.getId());

		if (!StringUtils.isEmpty(userDTO.getName())) {
			user.setName(userDTO.getName());
		}

		if (!StringUtils.isEmpty(userDTO.getEmail())) {
			user.setEmail(user.getEmail());
		}

		if (!StringUtils.isEmpty(userDTO.getStatus())) {
			user.setStatus(Status.valueOf(userDTO.getStatus()));
		}

		this.updateUser(user);

		return user;

	}

	public UserStatusUpdateDTO changeUserStatus(Integer id, String statusCode) {

		Status status = Util.getEnumFromString(Status.class, statusCode);

		User user = this.getUserById(id);

		if (user.getStatus() == status) {
			throw new RuntimeException("status is similar");
		}

		UserStatusUpdateDTO userDTO = new UserStatusUpdateDTO();
		userDTO.setId(user.getId());
		userDTO.setPreviousStatus(user.getStatus());
		userDTO.setStatus(status);

		user.setStatus(status);
		this.updateUser(user);

		return userDTO;

	}

	private void updateUser(User user) {
		userDao.updateUser(user);
	}

	public List<User> getUserList(String status) {

		return userDao.getUserList(Util.getEnumFromString(Status.class, status));
	}
}
