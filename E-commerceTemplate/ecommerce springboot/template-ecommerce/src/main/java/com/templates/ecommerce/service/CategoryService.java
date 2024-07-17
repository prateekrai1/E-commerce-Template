package com.templates.ecommerce.service;

import java.util.List;

import com.templates.ecommerce.dto.CategoryDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

public interface CategoryService {
	public CategoryDTO createCategory(CategoryDTO categorydto);
	public CategoryDTO updateCategory(Integer categoryId,CategoryDTO categorydto) throws ResourceNotFoundException;
	public void deleteCategory(Integer categoryId) throws ResourceNotFoundException;
	public List<CategoryDTO> AllCategories();
	
}
