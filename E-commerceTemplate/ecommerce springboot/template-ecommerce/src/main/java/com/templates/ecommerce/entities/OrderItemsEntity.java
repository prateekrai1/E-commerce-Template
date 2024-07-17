package com.templates.ecommerce.entities;

import java.math.BigDecimal;
import java.security.Timestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemsEntity {
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int item_id;
	 
	    @ManyToOne
	    @JoinColumn(name = "order_id")
	    private OrderEntity order;
	 
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private ProductEntity product;
	 
	    @Column(nullable = false)
	    private int quantity;
	 
	    @Column(nullable = false)
	    private BigDecimal price;
	    
}
