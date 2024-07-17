package com.templates.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.templates.ecommerce.dto.ProductDetailsDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.ProductDetailsService;

@RestController
@RequestMapping("/product-details")
public class ProductDetailsController {
	
	@Autowired
	ProductDetailsService productdetailservice;
	
	@PostMapping("/add-product-details")
	public ResponseEntity<ProductDetailsDTO> addProductdetails(ProductDetailsDTO productdetails) throws ResourceNotFoundException{
		ProductDetailsDTO product = productdetailservice.addProductdetails(productdetails);
		return new ResponseEntity<ProductDetailsDTO>(product,HttpStatus.CREATED);
	}
	
	@PutMapping("/edit-product-details/{productdetails_id}")
	public ResponseEntity<ProductDetailsDTO> editProductdetails(@PathVariable int productdetails_id,ProductDetailsDTO productdetails) throws ResourceNotFoundException{
		ProductDetailsDTO product = productdetailservice.editProductdetails(productdetails_id, productdetails);
		return new ResponseEntity<ProductDetailsDTO>(product,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete-product-details/{productdetails_id}")
	public ApiResponse deleteproductdetails(@PathVariable int productdetails_id) throws ResourceNotFoundException{
		productdetailservice.deleteProductdetails(productdetails_id);
		return new ApiResponse("Details deleted successfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all-details")
	public ResponseEntity<List<ProductDetailsDTO>> allProductdetails() throws ResourceNotFoundException{
		List<ProductDetailsDTO> product = productdetailservice.getAllProductdetails();
		return new ResponseEntity<List<ProductDetailsDTO>>(product,HttpStatus.ACCEPTED);
	}
	
}
