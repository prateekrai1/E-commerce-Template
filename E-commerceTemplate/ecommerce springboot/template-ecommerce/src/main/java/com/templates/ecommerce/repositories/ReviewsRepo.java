package com.templates.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.templates.ecommerce.entities.ReviewsEntity;

public interface ReviewsRepo extends JpaRepository<ReviewsEntity, Integer>{
	
	@Query("SELECT r FROM ReviewsEntity r WHERE r.product_id.productId = :product_id")
	public List<ReviewsEntity> findAllReviewsPerProduct(@Param("product_id") int product_id);
	
	@Query("SELECT r FROM ReviewsEntity r WHERE r.user_id.userId = :user_id")
	public List<ReviewsEntity> findAllReviewsPerUser(@Param("user_id") Integer user_id);
}
