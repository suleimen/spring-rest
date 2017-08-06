package com.swat.controller;

import com.swat.dto.UserDTO;
import com.swat.dto.UserStatusUpdateDTO;
import com.swat.model.User;
import com.swat.service.UserService;
import com.swat.util.DefaultResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController extends AbstractRestController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody DefaultResponse getUserById(@RequestParam(required = true) Integer id) {

		User user = userService.getUserById(id);
		return new DefaultResponse(user).SUCCESS();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody DefaultResponse createUser(@RequestBody @Valid UserDTO userDTO) {

		User user = userService.createUser(userDTO);
		return new DefaultResponse(user).SUCCESS();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody DefaultResponse updateUser(@RequestBody UserDTO userDTO) {

		User user = userService.updateUser(userDTO);
		return new DefaultResponse(user).SUCCESS();
	}

	@RequestMapping(value = "/{id}/{status}", method = RequestMethod.PUT)
	public @ResponseBody DefaultResponse changeUserStatus(@PathVariable("id") Integer id,
			@PathVariable("status") String statusCode) {

		UserStatusUpdateDTO userDTO = userService.changeUserStatus(id, statusCode);

		return new DefaultResponse(userDTO).SUCCESS();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody DefaultResponse getUserList(@RequestParam String status) {

		List<User> list = userService.getUserList(status);
		return new DefaultResponse(list).SUCCESS();
	}

}
