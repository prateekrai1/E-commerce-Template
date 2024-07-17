package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.ReviewsDTO;
import com.templates.ecommerce.entities.ProductEntity;
import com.templates.ecommerce.entities.ReviewsEntity;
import com.templates.ecommerce.entities.UserEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.ProductRepo;
import com.templates.ecommerce.repositories.ReviewsRepo;
import com.templates.ecommerce.repositories.UserRepo;
import com.templates.ecommerce.service.ReviewsService;


@Service
public class ReviewsServiceImpl implements ReviewsService {

	private ReviewsRepo reviewsrepo;
	private ProductRepo productrepo;
	private UserRepo userrepo;
	private ModelMapper modelmap;

	@Autowired
	public ReviewsServiceImpl(ReviewsRepo reviewsrepo, ProductRepo productrepo, UserRepo userrepo,
			ModelMapper modelmap) {
		this.reviewsrepo = reviewsrepo;
		this.productrepo = productrepo;
		this.userrepo = userrepo;
		this.modelmap = modelmap;
	}

	@Override
	public ReviewsDTO addReview(ReviewsDTO reviewdto) throws ResourceNotFoundException {
		UserEntity user = this.userrepo.findById(reviewdto.getUser_id()).orElseThrow(
				() -> new ResourceNotFoundException("Could not find User with Id: + " + reviewdto.getUser_id()));
		ProductEntity product = this.productrepo.findById(reviewdto.getProduct_id()).orElseThrow(
				() -> new ResourceNotFoundException("Could not find Product with Id: + " + reviewdto.getProduct_id()));

		ReviewsEntity review = dtoToEntity(reviewdto);
		review.setUser_id(user);
		review.setProduct_id(product);
		ReviewsEntity save = reviewsrepo.save(review);
		return entityToDto(save);
	}

	@Override
	public ReviewsDTO editReview(int review_id, ReviewsDTO reviewdto) throws ResourceNotFoundException {
		ReviewsEntity review = this.reviewsrepo.findById(review_id)
				.orElseThrow(() -> new ResourceNotFoundException("Could not find Review with Id: " + review_id));
		review.setRating(reviewdto.getReview());
		reviewsrepo.save(review);
		return entityToDto(review);
	}

	@Override
	public void deleteReview(int review_id) throws ResourceNotFoundException {
		ReviewsEntity review = this.reviewsrepo.findById(review_id)
				.orElseThrow(() -> new ResourceNotFoundException("Could not find Review with Id: " + review_id));
		reviewsrepo.delete(review);
	}

	@Override
	public List<ReviewsDTO> allReviews() {
		List<ReviewsEntity> reviews = this.reviewsrepo.findAll();
		return reviews.stream().map((review)-> entityToDto(review)).collect(Collectors.toList());
	}

	@Override
	public List<ReviewsDTO> getReviewsPerProduct(int product_id) throws ResourceNotFoundException {
		ProductEntity product = this.productrepo.findById(product_id).orElseThrow(
				() -> new ResourceNotFoundException("Could not find Product with Id: + " + product_id));
		List<ReviewsEntity> reviews = reviewsrepo.findAllReviewsPerProduct(product_id);
		return reviews.stream().map((review) -> entityToDto(review)).collect(Collectors.toList());
	}

	@Override
	public List<ReviewsDTO> getReviewsPerUser(Integer user_id) throws ResourceNotFoundException {
		UserEntity user = this.userrepo.findById(user_id).orElseThrow(
				() -> new ResourceNotFoundException("Could not find User with Id: + " + user_id));
		List<ReviewsEntity> reviews = reviewsrepo.findAllReviewsPerUser(user_id);
		return reviews.stream().map((item)-> entityToDto(item)).collect(Collectors.toList());
	}

	private ReviewsEntity dtoToEntity(ReviewsDTO reviewDto) throws ResourceNotFoundException {
		ReviewsEntity reviewEntity = new ReviewsEntity();
		reviewEntity.setRating(reviewDto.getReview());
		
		UserEntity user = userrepo.findById(reviewDto.getUser_id())
				.orElseThrow(() -> new ResourceNotFoundException("Could not find User with Id: " + reviewDto.getUser_id()));
		ProductEntity product = productrepo.findById(reviewDto.getProduct_id())
				.orElseThrow(() -> new ResourceNotFoundException("Could not find Product with Id: " + reviewDto.getProduct_id()));

		reviewEntity.setUser_id(user);
		reviewEntity.setProduct_id(product);
		return reviewEntity;
	}

	private ReviewsDTO entityToDto(ReviewsEntity reviewentity) {
		ReviewsDTO reviewDto = new ReviewsDTO();
		reviewDto.setReviews_id(reviewentity.getReview_id());
		reviewDto.setUser_id(reviewentity.getUser_id().getUserId());
		reviewDto.setProduct_id(reviewentity.getProduct_id().getProductId());
		reviewDto.setReview(reviewentity.getRating());
		return reviewDto;
	}

}
