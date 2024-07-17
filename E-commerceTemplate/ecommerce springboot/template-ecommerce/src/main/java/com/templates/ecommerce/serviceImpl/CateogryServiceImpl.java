package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.CategoryDTO;
import com.templates.ecommerce.entities.CategoryEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.CategoryRepo;
import com.templates.ecommerce.service.CategoryService;

@Service
public class CateogryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryrepo;
	
	@Autowired
	ModelMapper modelmap;

	@Override
	public CategoryDTO createCategory(CategoryDTO categorydto) {
		CategoryEntity category = createCategoryInEntity(categorydto);
		CategoryEntity saved = categoryrepo.save(category);
		return entityToDTO(saved);
	}

	@Override
	public CategoryDTO updateCategory(Integer categoryId, CategoryDTO categorydto) throws ResourceNotFoundException {
		CategoryEntity category = this.categoryrepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Cannot Find Category with Id: " + categoryId));
		category.setCategoryName(categorydto.getCategoryName());
		category.setDescription(categorydto.getDescription());
		CategoryEntity saved = categoryrepo.save(category);
		return entityToDTO(saved);
	}

	@Override
	public void deleteCategory(Integer categoryId) throws ResourceNotFoundException{
		CategoryEntity category = this.categoryrepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Cannot Find Category with Id: " + categoryId));
		categoryrepo.delete(category);
	}

	@Override
	public List<CategoryDTO> AllCategories() {
		List<CategoryEntity> categoriesList = categoryrepo.findAll();
		return categoriesList.stream().map((category)-> this.entityToDTO(category)).collect(Collectors.toList());
	}
	
	private CategoryEntity createCategoryInEntity(CategoryDTO cat) {
		return this.modelmap.map(cat, CategoryEntity.class);
	}
	private CategoryDTO entityToDTO(CategoryEntity cat) {
		return this.modelmap.map(cat, CategoryDTO.class);
	}
}
