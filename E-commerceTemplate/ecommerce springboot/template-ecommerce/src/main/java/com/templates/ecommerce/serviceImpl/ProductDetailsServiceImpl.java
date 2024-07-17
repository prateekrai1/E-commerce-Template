package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.ProductDetailsDTO;
import com.templates.ecommerce.entities.ProductDetailsEntity;
import com.templates.ecommerce.entities.ProductEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.ProductDetailsRepo;
import com.templates.ecommerce.repositories.ProductRepo;
import com.templates.ecommerce.service.ProductDetailsService;


@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

	private ProductDetailsRepo productdetailsrepo;
	private ProductRepo productrepo;
	private ModelMapper modelmap;

	@Autowired
	public ProductDetailsServiceImpl(ProductDetailsRepo productdetailsrepo, ProductRepo productrepo,
			ModelMapper modelmap) {
		this.productdetailsrepo = productdetailsrepo;
		this.productrepo = productrepo;
		this.modelmap = modelmap;
	}

	@Override
	public ProductDetailsDTO addProductdetails(ProductDetailsDTO productdetails) throws ResourceNotFoundException {
		ProductEntity prod = productrepo.findById(productdetails.getProduct_id())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Could not find Product with Id: " + productdetails.getProduct_id()));
		ProductDetailsEntity productdetails1 = dtoToEntity(productdetails);
		productdetails1.setProduct_id(prod);
		ProductDetailsEntity save = productdetailsrepo.save(productdetails1);
		return entityToDto(save);
	}

	@Override
	public ProductDetailsDTO editProductdetails(int productDetails_id, ProductDetailsDTO productdetails) throws ResourceNotFoundException {
		ProductDetailsEntity productdetailsentity = this.productdetailsrepo.findById(productDetails_id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Could not find any details with ProductDetails_id : " + productDetails_id));
		productdetailsentity.setFit(productdetails.getFit());
		productdetailsentity.setLength(productdetails.getLength());
		productdetailsentity.setSize(productdetails.getSize());
		productdetailsrepo.save(productdetailsentity);
		return entityToDto(productdetailsentity);
	}

	@Override
	public void deleteProductdetails(int productDetails_id) throws ResourceNotFoundException {
		ProductDetailsEntity productdetailsentity = this.productdetailsrepo.findById(productDetails_id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Could not find any details with ProductDetails_id : " + productDetails_id));
		productdetailsrepo.delete(productdetailsentity);
	}

	@Override
	public List<ProductDetailsDTO> getAllProductdetails() {
		List<ProductDetailsEntity> alldetails = this.productdetailsrepo.findAll();
		return alldetails.stream().map((detail) -> entityToDto(detail)).collect(Collectors.toList());
	}

	private ProductDetailsEntity dtoToEntity(ProductDetailsDTO productdetails) throws ResourceNotFoundException {
		ProductDetailsEntity product = new ProductDetailsEntity();
		ProductEntity prod = productrepo.findById(productdetails.getProduct_id())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Could not find Product with Id: " + productdetails.getProduct_id()));

		product.setFit(productdetails.getFit());
		product.setLength(productdetails.getLength());
		product.setSize(productdetails.getSize());
		product.setMaterial(productdetails.getMaterial());
		product.setPattern(productdetails.getPattern());
		product.setProduct_type(product.getProduct_type());
		product.setProduct_id(prod);
		product.setProduct_details_id(productdetails.getProductdetails_id());
		product.setColor(productdetails.getColor());
		return product;
	}

	private ProductDetailsDTO entityToDto(ProductDetailsEntity productDetails) {
		ProductDetailsDTO productdto = new ProductDetailsDTO();
		productdto.setFit(productDetails.getFit());
		productdto.setLength(productDetails.getLength());
		productdto.setSize(productDetails.getSize());
		productdto.setMaterial(productDetails.getMaterial());
		productdto.setPattern(productDetails.getPattern());
		productdto.setProduct_type(productDetails.getProduct_type());
		productdto.setProduct_id(productDetails.getProduct_id().getProductId());
		productdto.setProductdetails_id(productDetails.getProduct_details_id());
		productdto.setColor(productDetails.getColor());
		return productdto;
	}

}
