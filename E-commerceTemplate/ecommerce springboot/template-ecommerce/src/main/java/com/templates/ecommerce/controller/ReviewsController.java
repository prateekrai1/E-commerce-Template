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

import com.templates.ecommerce.dto.ReviewsDTO;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.responses.ApiResponse;
import com.templates.ecommerce.service.ReviewsService;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
	
	@Autowired
	ReviewsService reviewsService;
	
	@PostMapping("/add-review")
	public ResponseEntity<ReviewsDTO> addReview(ReviewsDTO reviewdto) throws ResourceNotFoundException{
		ReviewsDTO review = reviewsService.addReview(reviewdto);
		return new ResponseEntity<ReviewsDTO>(review,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-review/{review_id}")
	public ResponseEntity<ReviewsDTO> updateReview(@PathVariable int review_id, ReviewsDTO reviewdto) throws ResourceNotFoundException{
		ReviewsDTO updatedReview = reviewsService.editReview(review_id, reviewdto);
		return new ResponseEntity<ReviewsDTO>(updatedReview,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete-review/{review_id}")
	public ApiResponse deleteReview(@PathVariable int review_id) throws ResourceNotFoundException {
		reviewsService.deleteReview(review_id);
		return new ApiResponse("Review Deleted Successfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all-reviews")
	public ResponseEntity<List<ReviewsDTO>> allReviews(){
		List<ReviewsDTO> reviews = reviewsService.allReviews();
		return new ResponseEntity<List<ReviewsDTO>>(reviews,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all-reviews-per-product/{product_id}")
	public ResponseEntity<List<ReviewsDTO>> getReviewsByProduct(@PathVariable int product_id) throws ResourceNotFoundException{
		List<ReviewsDTO> reviewsByProduct = reviewsService.getReviewsPerProduct(product_id);
		return new ResponseEntity<List<ReviewsDTO>>(reviewsByProduct,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all-reviews-per-user/{user_id}")
	public ResponseEntity<List<ReviewsDTO>> getReviewsByProduct(@PathVariable Integer user_id) throws ResourceNotFoundException{
		List<ReviewsDTO> reviewsByUser = reviewsService.getReviewsPerUser(user_id);
		return new ResponseEntity<List<ReviewsDTO>>(reviewsByUser,HttpStatus.ACCEPTED);
	}
}
