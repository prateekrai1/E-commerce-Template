package com.templates.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.templates.ecommerce.dto.UserDTO;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto){
		UserDTO user = this.userService.createUser(userdto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userid}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto, @PathVariable int userid) throws Exception{
		UserDTO user = this.userService.updateUser(userid, userDto);
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{userid}")
	public ApiResponse deleteUser(@PathVariable int userid) throws Exception{
		userService.deleteUser(userid);
		return new ApiResponse("User successfully deleted",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDTO>> allUsers(){
		List<UserDTO> users = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(users,HttpStatus.ACCEPTED);
	}

}
