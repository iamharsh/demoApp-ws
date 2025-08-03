package com.taiga.app.ws.demows.ui.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taiga.app.ws.demows.ui.model.request.UserDetailsRequestModel;
import com.taiga.app.ws.demows.ui.model.response.UserRest;
import com.taiga.app.ws.demows.userservice.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	Map<String, UserRest> users;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", required=false) String sort){//required does not work on primitive data type 
		return "get user called page = "+page +" limit = "+limit +" sort = "+sort;
	}
	
//	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//	public UserRest getUser(@PathVariable String userId){
//		UserRest retVal = new UserRest();
//		retVal.setFirstName("Harsh");
//		retVal.setLastName("S");
//		retVal.setEmail("test@test.com");
//		retVal.setUserId(userId);
//		return retVal;
//	}
	
	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> getUserWithStatus(@PathVariable String userId){
//		UserRest retVal = new UserRest();
//		retVal.setFirstName("Harsh");
//		retVal.setLastName("S");
//		retVal.setEmail("test@test.com");
//		retVal.setUserId(userId);
		
		if(users.containsKey(userId)){
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
//		return new ResponseEntity<UserRest>(retVal, HttpStatus.OK);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser( @RequestBody UserDetailsRequestModel userDetails){
		UserRest retVal =this.userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(retVal, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel userDetails){
		UserRest StoredUserDetails = users.get(userId);
		StoredUserDetails.setFirstName(userDetails.getFirstName());
		StoredUserDetails.setLastName(userDetails.getLastName());
		users.put(userId, StoredUserDetails);
		
		return StoredUserDetails;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		users.remove(id);
		
		return ResponseEntity.noContent().build();
		
	}

}
