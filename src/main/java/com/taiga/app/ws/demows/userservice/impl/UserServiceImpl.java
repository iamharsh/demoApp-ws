package com.taiga.app.ws.demows.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiga.app.ws.demows.shared.Utils;
import com.taiga.app.ws.demows.ui.model.request.UserDetailsRequestModel;
import com.taiga.app.ws.demows.ui.model.response.UserRest;
import com.taiga.app.ws.demows.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;
	Utils utils;

	public UserServiceImpl() {
	}

	@Autowired //constructor based dependency injection
	public UserServiceImpl(Utils utils) {
		this.utils=utils;
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest retVal = new UserRest();
		retVal.setFirstName(userDetails.getFirstName());
		retVal.setLastName(userDetails.getLastName());
		retVal.setEmail(userDetails.getEmail());

		String userId = utils.generateUserId();
		retVal.setUserId(userId);
		if (users == null) {
			users = new HashMap<>();
		}
		users.put(userId, retVal);
		return retVal;
	}

}
