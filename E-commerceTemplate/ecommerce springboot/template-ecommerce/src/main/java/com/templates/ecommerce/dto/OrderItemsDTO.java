package com.templates.ecommerce.dto;

import java.math.BigDecimal;

import com.templates.ecommerce.entities.OrderEntity;
import com.templates.ecommerce.entities.ProductEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemsDTO {
	
	private int items_id;
	private int order_id;
	private int product_id;
	private int quantity;
	private BigDecimal price;
}
