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

import com.templates.ecommerce.dto.OrderItemsDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.OrderItemsService;

@RestController
@RequestMapping("/OrderItems")
public class OrderItemsController {
	
	@Autowired
	OrderItemsService orderItemService;
	
	@PostMapping("/addToOrder")
	public ResponseEntity<OrderItemsDTO> addtoOrder(@RequestBody OrderItemsDTO orderitemsdto) throws Exception{
		OrderItemsDTO add = orderItemService.orderedItems(orderitemsdto);
		return new ResponseEntity<OrderItemsDTO>(add,HttpStatus.CREATED);
	}
	
	@PutMapping("/editOrderItem/{orderItemsId}")
	public ResponseEntity<OrderItemsDTO> updateItemstoOrder(@RequestBody OrderItemsDTO orderitemsdto, @PathVariable int orderItemsId) throws Exception{
		OrderItemsDTO update = orderItemService.updateOrderItems(orderItemsId, orderitemsdto);
		return new ResponseEntity<OrderItemsDTO>(update,HttpStatus.ACCEPTED);
	} 
	
	@DeleteMapping("/deleteOrderItem/{orderItemsId}")
	public ApiResponse deleteItems(@PathVariable int orderItemsId) throws ResourceNotFoundException {
		orderItemService.deleteItems(orderItemsId);
		return new ApiResponse("Ordered Item successfully deleted",HttpStatus.ACCEPTED );
	}
	
	@GetMapping("/allItemsOrdered")
	public ResponseEntity<List<OrderItemsDTO>> allItemsToOrder(){
		List<OrderItemsDTO> items = orderItemService.allItems();
		return new ResponseEntity<List<OrderItemsDTO>>(items,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allItemsPerOrder/{orderId}")
	public ResponseEntity<List<OrderItemsDTO>> allItemsPerOrder(@PathVariable int orderId) throws ResourceNotFoundException{
		List<OrderItemsDTO> items = orderItemService.allItemsPerOrder(orderId);
		return new ResponseEntity<List<OrderItemsDTO>>(items,HttpStatus.ACCEPTED);
	}
	
}
