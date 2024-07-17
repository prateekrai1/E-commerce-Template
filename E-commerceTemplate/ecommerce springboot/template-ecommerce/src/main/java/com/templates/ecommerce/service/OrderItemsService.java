package com.templates.ecommerce.service;

import java.util.List;

import com.templates.ecommerce.dto.OrderItemsDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

public interface OrderItemsService {
	public OrderItemsDTO orderedItems(OrderItemsDTO orderitems) throws ResourceNotFoundException;
	public OrderItemsDTO updateOrderItems(int item_id, OrderItemsDTO orderitems) throws ResourceNotFoundException;
	public void deleteItems(int item_id) throws ResourceNotFoundException;
	public List<OrderItemsDTO> allItems();
	public List<OrderItemsDTO> allItemsPerOrder(int orderId) throws ResourceNotFoundException;
}
