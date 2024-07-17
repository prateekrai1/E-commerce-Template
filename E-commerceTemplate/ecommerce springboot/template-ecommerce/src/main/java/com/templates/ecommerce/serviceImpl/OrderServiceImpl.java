package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.OrderDTO;
import com.templates.ecommerce.entities.OrderEntity;
import com.templates.ecommerce.entities.UserEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.OrderRepo;
import com.templates.ecommerce.repositories.UserRepo;
import com.templates.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	OrderRepo orderrepo;
	UserRepo userrepo;
	ModelMapper modelmapper;

	public OrderServiceImpl(ModelMapper modelmapper, UserRepo userrepo, OrderRepo orderrepo) {
		super();
		this.modelmapper = modelmapper;
		this.orderrepo = orderrepo;
		this.userrepo = userrepo;
	}

	@Override
	public OrderDTO createOrder(OrderDTO orderdto) throws ResourceNotFoundException {
		UserEntity user = this.userrepo.findById(orderdto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find user with id:" + orderdto.getUserId()));

		OrderEntity orderEntity = dtoToEntity(orderdto);
		orderEntity.setCustomer(user);
		orderEntity.setOrderId(0);
		OrderEntity saved = orderrepo.save(orderEntity);
		return modelmapper.map(saved, OrderDTO.class);
	}

	@Override
	public OrderDTO updateOrder(int orderId, OrderDTO orderdto) throws ResourceNotFoundException {
		OrderEntity order = this.orderrepo.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find Order with id: " + orderId));
		order.setOrderDate(orderdto.getOrderDate());
		order.setStatus(orderdto.getStatus());
		order.setTotalAmount(orderdto.getTotalAmount());
		orderrepo.save(order);
		return modelmapper.map(order, OrderDTO.class);
	}

	@Override
	public void deleteOrder(int orderId) throws ResourceNotFoundException {
		OrderEntity order = this.orderrepo.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find Order with id: " + orderId));
		this.orderrepo.delete(order);
	}

	@Override
	public List<OrderDTO> orders() {
		List<OrderEntity> orders = this.orderrepo.findAll();
		return orders.stream().map(order -> this.EntitytoDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> ordersPerUser(int userId) throws ResourceNotFoundException {
		UserEntity user = this.userrepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find User with id: " + userId));
		List<OrderEntity> orders = this.orderrepo.getOrdersbyUser(userId);
		return orders.stream().map((order) -> EntitytoDTO(order)).collect(Collectors.toList());
	}

	private OrderEntity dtoToEntity(OrderDTO orderdto) {
		OrderEntity orderEntity = this.modelmapper.map(orderdto, OrderEntity.class);
		if (orderdto.getStatus().equals("")) {
			orderEntity.setStatus("Order Confirmed");
		}
		UserEntity customer = userrepo.findById(orderdto.getUserId()).orElse(null);
		orderEntity.setCustomer(customer);
		return orderEntity;
	}

	private OrderDTO EntitytoDTO(OrderEntity orderentity) {
		OrderDTO orderdto = modelmapper.map(orderentity, OrderDTO.class);
		orderdto.setUserId(orderentity.getCustomer().getUserId());
		return orderdto;
	}

}
