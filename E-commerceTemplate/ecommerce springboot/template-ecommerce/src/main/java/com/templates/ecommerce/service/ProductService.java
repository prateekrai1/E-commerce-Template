package com.templates.ecommerce.service;

import com.templates.ecommerce.dto.ProductDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

import java.util.*;
public interface ProductService {
	
	public ProductDTO addProduct(ProductDTO productdto) throws ResourceNotFoundException;
	public ProductDTO updateProduct(int productId, ProductDTO productdto) throws ResourceNotFoundException;
	public void deleteProduct(int productId) throws ResourceNotFoundException;
	public List<ProductDTO> getAllProducts();
}
