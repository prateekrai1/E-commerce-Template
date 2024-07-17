package com.templates.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.templates.ecommerce.entities.ProductDetailsEntity;

public interface ProductDetailsRepo extends JpaRepository<ProductDetailsEntity, Integer>{

}
