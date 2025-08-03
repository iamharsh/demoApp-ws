package com.taiga.app.ws.demows.userservice;

import com.taiga.app.ws.demows.ui.model.request.UserDetailsRequestModel;
import com.taiga.app.ws.demows.ui.model.response.UserRest;

public interface UserService {

	UserRest createUser(UserDetailsRequestModel userDetails);
}
