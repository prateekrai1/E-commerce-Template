package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.UserDTO;
import com.templates.ecommerce.entities.UserEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.UserRepo;
import com.templates.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDTO createUser(UserDTO userdto) {
		UserEntity user = this.createUserInEntity(userdto);
		UserEntity saved = this.userrepo.save(user);
		return getobjectfromEntity(saved);
	}

	@Override
	public UserDTO updateUser(int userid, UserDTO userdto) throws ResourceNotFoundException {
		UserEntity user = this.userrepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userid));

		user.setUsername(userdto.getUsername());
		user.setCity(userdto.getCity());
		user.setAddress(userdto.getAddress());
		user.setEmail(userdto.getEmail());
		user.setPhoneNumber(userdto.getPhoneNumber());
		user.setFirstName(userdto.getFirstName());
		user.setCountry(userdto.getCountry());
		user.setEmail(userdto.getEmail());
		user.setLastName(userdto.getFirstName());

		UserEntity updatedUser = userrepo.save(user);
		return this.modelmapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public void deleteUser(int userid) throws ResourceNotFoundException {
		UserEntity user = this.userrepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userid));
		userrepo.delete(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserEntity> users = this.userrepo.findAll();
		return users.stream().map(user -> this.getobjectfromEntity(user)).collect(Collectors.toList());
	}

	private UserEntity createUserInEntity(UserDTO userdto) {
		UserEntity user = this.modelmapper.map(userdto, UserEntity.class);
//		user.setUserId(userdto.get);
//		user.setUsername(userdto.getName());
//		user.setPassword(userdto.getPassword());
//		user.setPhone_number(userdto.getPhone_number());
//		user.setAddress(userdto.getAddress());
		return user;
	}

	private UserDTO getobjectfromEntity(UserEntity user) {
		UserDTO userdto = this.modelmapper.map(user, UserDTO.class);
//		userdto.setName(user.getUsername());
//		userdto.setPassword(user.getPassword());
//		userdto.setAddress(user.getAddress());
//		userdto.setPhone_number(user.getPhone_number());
		return userdto;
	}

}
