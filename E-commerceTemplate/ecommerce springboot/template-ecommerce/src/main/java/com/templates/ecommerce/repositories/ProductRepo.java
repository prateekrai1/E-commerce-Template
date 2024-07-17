package com.templates.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.templates.ecommerce.entities.ProductEntity;


public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
	
//	@Query("SELECT p FROM ProductEntity p WHERE p.category.categoryId = :categoryId")
//    List<ProductEntity> findProductsByCategory(@Param("categoryId") int categoryId);	
}
