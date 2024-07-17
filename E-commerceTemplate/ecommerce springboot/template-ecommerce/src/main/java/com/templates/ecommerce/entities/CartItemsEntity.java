package com.templates.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Cart_Items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartItem_id;
	
	@OneToOne
	private ProductEntity product_id;
	
	@ManyToOne
	private CartEntity cart_id;
}
