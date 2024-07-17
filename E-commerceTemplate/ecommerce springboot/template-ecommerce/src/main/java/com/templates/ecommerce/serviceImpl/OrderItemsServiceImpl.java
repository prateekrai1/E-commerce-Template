package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.OrderItemsDTO;
import com.templates.ecommerce.entities.OrderEntity;
import com.templates.ecommerce.entities.OrderItemsEntity;
import com.templates.ecommerce.entities.ProductEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.OrderItemsRepo;
import com.templates.ecommerce.repositories.OrderRepo;
import com.templates.ecommerce.repositories.ProductRepo;
import com.templates.ecommerce.service.OrderItemsService;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
	
	private final ProductRepo productrepo;
	private final OrderRepo orderrepo;
	private final ModelMapper modelmap;
	private final OrderItemsRepo itemsrepo;

	@Autowired
	public OrderItemsServiceImpl(ProductRepo productrepo, OrderRepo orderrepo, ModelMapper modelmap,OrderItemsRepo itemsrepo) {
		this.productrepo = productrepo;
		this.orderrepo = orderrepo;
		this.modelmap = modelmap;
		this.itemsrepo = itemsrepo;
	}

	@Override
	public OrderItemsDTO orderedItems(OrderItemsDTO orderitems) throws ResourceNotFoundException {
		OrderEntity order = this.orderrepo.findById(orderitems.getOrder_id()).orElseThrow(
				() -> new ResourceNotFoundException("Could not find order with ID: " + orderitems.getOrder_id()));
		ProductEntity product = this.productrepo.findById(orderitems.getProduct_id()).orElseThrow(
				() -> new ResourceNotFoundException("Could not find product with ID: " + orderitems.getProduct_id()));
		OrderItemsEntity Item = dtoToEntity(orderitems);
		Item.setOrder(order);
		Item.setProduct(product);
		OrderItemsEntity save = itemsrepo.save(Item);
		return entityToDto(save);
	}

	@Override
	public OrderItemsDTO updateOrderItems(int item_id, OrderItemsDTO orderitems) throws ResourceNotFoundException {
		OrderItemsEntity order = this.itemsrepo.findById(item_id).orElseThrow(
				() -> new ResourceNotFoundException("Could not find item with ID: " + item_id));
		order.setPrice(orderitems.getPrice());
		order.setQuantity(orderitems.getQuantity());
		itemsrepo.save(order);
		return entityToDto(order);
	}

	@Override
	public void deleteItems(int item_id) throws ResourceNotFoundException {
		OrderItemsEntity item = this.itemsrepo.findById(item_id).orElseThrow(
				() -> new ResourceNotFoundException("Could not find item with ID: " + item_id));
		itemsrepo.delete(item);
	}

	@Override
	public List<OrderItemsDTO> allItems() {
		List<OrderItemsEntity> items = itemsrepo.findAll();
		return items.stream().map((item)-> this.entityToDto(item)).collect(Collectors.toList());
	}
	
	@Override
	public List<OrderItemsDTO> allItemsPerOrder(int orderId) throws ResourceNotFoundException {
		OrderEntity order = this.orderrepo.findById(orderId).orElseThrow(
				() -> new ResourceNotFoundException("Could not find order with ID: " + orderId));
		List<OrderItemsEntity> items = itemsrepo.allItemsPerOrder(orderId);
		return items.stream().map((item)-> entityToDto(item)).collect(Collectors.toList());
	}

	
	private OrderItemsEntity dtoToEntity(OrderItemsDTO item) throws ResourceNotFoundException {
		OrderItemsEntity items = modelmap.map(item,OrderItemsEntity.class);
		OrderEntity order = this.orderrepo.findById(item.getOrder_id()).orElseThrow(
				() -> new ResourceNotFoundException("Could not find order with ID: " + item.getOrder_id()));
		ProductEntity product = this.productrepo.findById(item.getProduct_id()).orElseThrow(
				() -> new ResourceNotFoundException("Could not find product with ID: " + item.getProduct_id()));
		item.setOrder_id(order.getOrderId());
		item.setProduct_id(product.getProductId());
		return items;
	}
	
	private OrderItemsDTO entityToDto(OrderItemsEntity entity) {
		OrderItemsDTO items = modelmap.map(entity, OrderItemsDTO.class);
		items.setOrder_id(entity.getOrder().getOrderId());
		items.setProduct_id(entity.getProduct().getProductId());
		return items;
	}
	
}
