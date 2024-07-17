package com.templates.ecommerce.service;

import java.util.List;

import com.templates.ecommerce.dto.ProductDetailsDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

public interface ProductDetailsService {
	
	public ProductDetailsDTO addProductdetails(ProductDetailsDTO productdetails) throws ResourceNotFoundException;
	public ProductDetailsDTO editProductdetails(int productDetails_id, ProductDetailsDTO productdetails) throws ResourceNotFoundException;
	public void deleteProductdetails(int productDetails_id) throws ResourceNotFoundException;
	public List<ProductDetailsDTO> getAllProductdetails();
}
