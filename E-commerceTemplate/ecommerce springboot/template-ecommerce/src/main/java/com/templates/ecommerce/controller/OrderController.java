package com.templates.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.templates.ecommerce.dto.OrderDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderservice;
	
	@PostMapping("/ordernow")
	public ResponseEntity<OrderDTO> generateOrder(@RequestBody OrderDTO orderdto) throws ResourceNotFoundException{
		OrderDTO order = this.orderservice.createOrder(orderdto);
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@PutMapping("/editorder/{orderId}")
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderdto, @PathVariable int orderId) throws ResourceNotFoundException{
		OrderDTO order = this.orderservice.updateOrder(orderId, orderdto);
		return new ResponseEntity<OrderDTO>(order,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteOrder/{orderId}")
	public ApiResponse deleteOrder(@PathVariable int orderId) throws ResourceNotFoundException {
		this.orderservice.deleteOrder(orderId);
		return new ApiResponse("Order Successfully Deleted",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/allOrders")
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		List<OrderDTO> orders = orderservice.orders();
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/orderByUser/{userId}")
	public ResponseEntity<List<OrderDTO>> ordersByUser(@PathVariable int userId) throws ResourceNotFoundException{
		List<OrderDTO> orders = orderservice.ordersPerUser(userId);
		return new ResponseEntity<List<OrderDTO>>(orders,HttpStatus.ACCEPTED);
	}

}
