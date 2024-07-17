package com.templates.ecommerce.dto;

import java.sql.Timestamp;
import java.util.List;

import com.templates.ecommerce.entities.CartItemsEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {

	private Integer id;

	private Integer userId;
	
	private Timestamp addedDate;

	private Timestamp updateDate;
		
	@Override
	public String toString() {
		return "CartDTO [id=" + id + ", userId=" + userId + ", addedDate=" + addedDate + ", updateDate=" + updateDate
				+ "]";
	}
}
