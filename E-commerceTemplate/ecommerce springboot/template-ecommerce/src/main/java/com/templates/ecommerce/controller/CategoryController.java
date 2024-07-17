package com.templates.ecommerce.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.templates.ecommerce.dto.CategoryDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryservice;
	
	@PostMapping("/create-category")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categorydto){
		CategoryDTO category = categoryservice.createCategory(categorydto);
		return new ResponseEntity<CategoryDTO>(category,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-category/{categoryID}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer categoryID, @RequestBody CategoryDTO categorydto) throws ResourceNotFoundException{
		CategoryDTO category = categoryservice.updateCategory(categoryID,categorydto);
		return new ResponseEntity<CategoryDTO>(category,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete-category/{categoryID}")
	public ApiResponse deleteCategory(@PathVariable Integer categoryID) throws ResourceNotFoundException{
		categoryservice.deleteCategory(categoryID);
		return new ApiResponse("Category Successfully Deleted",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all-categories")
	public ResponseEntity<List<CategoryDTO>> AllCategories(){
		List<CategoryDTO> categories = categoryservice.AllCategories();
		return new ResponseEntity<List<CategoryDTO>>(categories,HttpStatus.ACCEPTED);
	}
}
