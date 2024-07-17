package com.templates.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.templates.ecommerce.entities.OrderItemsEntity;

public interface OrderItemsRepo extends JpaRepository<OrderItemsEntity, Integer>{
	
	@Query("SELECT o FROM OrderItemsEntity o where o.order.id = :orderId")
	public List<OrderItemsEntity> allItemsPerOrder(@Param ("orderId") int orderId);
	
}
