package com.templates.ecommerce.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private String product_name;
	private String description;
	private int category_id;
	private BigDecimal price;
	private int quantity;
	private String imageUrl;
	
	
	
	@Override
	public String toString() {
		return "ProductDTO [product_name=" + product_name + ", description=" + description + ", category_id="
				+ category_id + ", price=" + price + ", quantity=" + quantity + ", imageUrl=" + imageUrl + "]";
	}

}
