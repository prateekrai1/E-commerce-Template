package com.templates.ecommerce.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(nullable = false)
	private String product_name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private CategoryEntity category;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	private int quantity;

	private String imageUrl;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
	private List<OrderItemsEntity> orderItems;
	
	@OneToMany(mappedBy ="product_id", fetch = FetchType.EAGER)
	private List<ReviewsEntity> reviews;
	
	@OneToMany(mappedBy = "product_id" , fetch = FetchType.EAGER)
	private List<ProductDetailsEntity> productDetails;
	
	@ManyToOne
	private CartEntity cart;

}
