package com.templates.ecommerce.service;

import com.templates.ecommerce.dto.UserDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

import java.util.*;

public interface UserService {
	public UserDTO createUser(UserDTO userdto);
	public UserDTO updateUser(int userid, UserDTO userdto) throws Exception;
	public void deleteUser(int userid) throws ResourceNotFoundException;
	public List<UserDTO> getAllUsers();
}
