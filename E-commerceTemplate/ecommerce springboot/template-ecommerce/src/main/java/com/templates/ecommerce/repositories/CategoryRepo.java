package com.templates.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.templates.ecommerce.entities.CategoryEntity;

import io.swagger.v3.oas.models.security.SecurityScheme.In;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer>{
	
}
