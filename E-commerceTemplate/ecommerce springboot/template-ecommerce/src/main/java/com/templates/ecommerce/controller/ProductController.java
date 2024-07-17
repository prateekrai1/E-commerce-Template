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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.templates.ecommerce.dto.ProductDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productservice;
	
	@PostMapping("/addproduct")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productdto) throws ResourceNotFoundException{
		ProductDTO product = this.productservice.addProduct(productdto);
		return new ResponseEntity<ProductDTO>(product,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateProduct/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productdto, @PathVariable int productId) throws ResourceNotFoundException{
		ProductDTO product = this.productservice.updateProduct(productId, productdto);
		return new ResponseEntity<ProductDTO>(product,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteproduct/{productId}")
	public ApiResponse deleteProduct(@PathVariable int productId) throws ResourceNotFoundException {
		this.productservice.deleteProduct(productId);
		return new ApiResponse("Product deleted successfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allProducts")
	public ResponseEntity<List<ProductDTO>> allProducts(){
		List<ProductDTO> products = productservice.getAllProducts();
		return new ResponseEntity<List<ProductDTO>>(products,HttpStatus.ACCEPTED);
	}

}
