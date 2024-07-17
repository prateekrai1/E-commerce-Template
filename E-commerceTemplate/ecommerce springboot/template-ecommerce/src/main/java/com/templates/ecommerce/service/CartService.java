package com.templates.ecommerce.service;

import java.util.List;

import com.templates.ecommerce.dto.CartDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

public interface CartService {
	
	public CartDTO addToCart(CartDTO cartdto) throws ResourceNotFoundException;
	public CartDTO updateCart(Integer cartId, CartDTO cartdto) throws ResourceNotFoundException;
	public void clearCart(Integer cartId) throws ResourceNotFoundException;
	public List<CartDTO>getAllCarts();
	
}
