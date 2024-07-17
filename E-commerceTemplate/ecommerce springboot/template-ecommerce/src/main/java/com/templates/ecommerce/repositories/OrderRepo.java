package com.templates.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.templates.ecommerce.entities.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer>{
	
	@Query("SELECT o FROM OrderEntity o WHERE o.customer.id = :userId")
	List<OrderEntity> getOrdersbyUser(@Param ("userId") int userId);
}
