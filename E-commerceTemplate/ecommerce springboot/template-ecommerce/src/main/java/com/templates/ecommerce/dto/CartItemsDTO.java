package com.templates.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemsDTO {
	
	private Integer cart_item_id;
	private int product_id;
	private Integer cart_id;

}
