package com.templates.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO {
	private int productdetails_id;
	private int product_id;
	private String size;
	private String color;
	private String material;
	private String product_type;
	private String pattern;
	private String length;
	private String fit;
}
