package com.templates.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
   
	private int userId;
	private LocalDate orderDate;
	private BigDecimal totalAmount;
	private String status;
}
