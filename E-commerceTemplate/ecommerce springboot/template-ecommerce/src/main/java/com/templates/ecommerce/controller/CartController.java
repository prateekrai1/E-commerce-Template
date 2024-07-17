package com.templates.ecommerce.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.templates.ecommerce.dto.CartDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.CartService;


@RestController
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	
	@PostMapping(value = "/add-to-cart",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO cartdto) throws ResourceNotFoundException{
		System.out.println(cartdto.toString());
		CartDTO response = cartservice.addToCart(cartdto);
		return new ResponseEntity<CartDTO>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-cart/{cart_id}")
	public ResponseEntity<CartDTO> updateCart(@RequestBody CartDTO cartdto, @PathVariable Integer cart_id) throws ResourceNotFoundException{
		CartDTO response = cartservice.updateCart(cart_id,cartdto);
		return new ResponseEntity<CartDTO>(response,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete-cart/{cart_id}")
	public ApiResponse removecart(@PathVariable Integer cart_id) throws ResourceNotFoundException{
		cartservice.clearCart(cart_id);
		return new ApiResponse("Cart Successfully Cleared",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllcarts")
	public ResponseEntity<List<CartDTO>> allcarts() {
		List<CartDTO> response = cartservice.getAllCarts();
		return new ResponseEntity<List<CartDTO>>(response,HttpStatus.ACCEPTED);
	}
}
