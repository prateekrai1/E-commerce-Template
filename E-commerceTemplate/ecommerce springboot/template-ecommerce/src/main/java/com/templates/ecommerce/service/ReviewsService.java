package com.templates.ecommerce.service;

import java.util.List;

import com.templates.ecommerce.dto.ReviewsDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;

public interface ReviewsService {
	public ReviewsDTO addReview(ReviewsDTO reviewdto) throws ResourceNotFoundException;
	public ReviewsDTO editReview(int review_id, ReviewsDTO reviewdto) throws ResourceNotFoundException;
	public void deleteReview(int review_id) throws ResourceNotFoundException;
	public List<ReviewsDTO> allReviews();
	public List<ReviewsDTO> getReviewsPerProduct(int product_id) throws ResourceNotFoundException;
	public List<ReviewsDTO> getReviewsPerUser(Integer user_id) throws ResourceNotFoundException;
}
