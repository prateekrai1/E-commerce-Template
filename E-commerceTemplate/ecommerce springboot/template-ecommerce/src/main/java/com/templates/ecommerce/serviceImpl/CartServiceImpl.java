package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.CartDTO;
import com.templates.ecommerce.entities.CartEntity;
import com.templates.ecommerce.entities.UserEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.CartRepo;
import com.templates.ecommerce.repositories.ProductRepo;
import com.templates.ecommerce.repositories.UserRepo;
import com.templates.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	UserRepo userrepo;
	ProductRepo productrepo;
	CartRepo cartrepo;
	
	@Autowired
	public CartServiceImpl(UserRepo userrepo,ProductRepo productrepo,CartRepo cartrepo) {
		this.userrepo = userrepo;
		this.productrepo = productrepo;
		this.cartrepo = cartrepo;
	}

	@Override
	public CartDTO addToCart(CartDTO cartdto) throws ResourceNotFoundException {
		CartEntity cart = cartrepo.save(DtoToEntity(cartdto));
		return EntityToDto(cart);
	}

	@Override
	public CartDTO updateCart(Integer cartId, CartDTO cartdto) throws ResourceNotFoundException {
		CartEntity cartObj = cartrepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Unable to find Cart with Id :" + cartId));
		cartObj.setUpdateDate(cartdto.getUpdateDate());
		cartObj.setAddedDate(cartdto.getAddedDate());
		CartEntity save = cartrepo.save(cartObj);
		return EntityToDto(save);
	}

	@Override
	public void clearCart(Integer cartId) throws ResourceNotFoundException {
		CartEntity cartObj = cartrepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Unable to find Cart with Id :" + cartId));
		cartrepo.delete(cartObj);
	}
 
	@Override
	public List<CartDTO> getAllCarts() {
		List<CartEntity> allcarts = cartrepo.findAll();
		return allcarts.stream().map((eachcart) -> EntityToDto(eachcart)).collect(Collectors.toList());
	}

	private CartDTO EntityToDto(CartEntity cartentity) {
		CartDTO cartdto = new CartDTO();
		cartdto.setId(cartentity.getId());
		cartdto.setUpdateDate(cartentity.getUpdateDate());
		cartdto.setAddedDate(cartentity.getAddedDate());
		cartdto.setUserId(cartentity.getUserId().getUserId());
		return cartdto;
	}

	private CartEntity DtoToEntity(CartDTO cartdto) throws ResourceNotFoundException {
		CartEntity cartentity = new CartEntity();
		UserEntity user = this.userrepo.findById(cartdto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Could not find User with id: " + cartdto.getUserId()));
		cartentity.setAddedDate(cartdto.getAddedDate());
		cartentity.setUpdateDate(cartdto.getUpdateDate());
		cartentity.setUserId(user);
		return cartentity;
	}

}
