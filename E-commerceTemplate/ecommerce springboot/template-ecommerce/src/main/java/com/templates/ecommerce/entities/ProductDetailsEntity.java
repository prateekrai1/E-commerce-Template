package com.templates.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Product_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_details_id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product_id;
	private String size;
	private String color;
	private String material;
	private String product_type;
	private String pattern;
	private String length;
	private String fit;
}
