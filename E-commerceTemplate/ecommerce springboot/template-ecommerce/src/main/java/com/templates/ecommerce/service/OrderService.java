package com.templates.ecommerce.service;

import java.util.List;

import com.templates.ecommerce.dto.OrderDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

public interface OrderService {
	
	public OrderDTO createOrder(OrderDTO order) throws ResourceNotFoundException;
	public OrderDTO updateOrder(int orderId, OrderDTO order) throws ResourceNotFoundException;
	public void deleteOrder(int orderId) throws ResourceNotFoundException;
	public List<OrderDTO> orders();
	public List<OrderDTO> ordersPerUser(int userId) throws ResourceNotFoundException;
}
