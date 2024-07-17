package com.templates.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDTO {
	private int reviews_id;
	private Integer user_id;
	private int product_id;
	private int review;
}
