package com.templates.ecommerce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.ecommerce.dto.ProductDTO;
import com.templates.ecommerce.entities.CategoryEntity;
import com.templates.ecommerce.entities.ProductEntity;
import com.templates.ecommerce.exceptions.ResourceNotFoundException;
import com.templates.ecommerce.repositories.CategoryRepo;
import com.templates.ecommerce.repositories.ProductRepo;
import com.templates.ecommerce.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
    private final ProductRepo productrepo;
    private final CategoryRepo categoryrepo;
    private final ModelMapper modelmapper;
	
	@Autowired
	public ProductServiceImpl(ProductRepo productrepo, CategoryRepo categoryrepo,ModelMapper modelmapper) {
		this.productrepo = productrepo;
		this.categoryrepo = categoryrepo;
		this.modelmapper = modelmapper;
	}

	@Override
	public ProductDTO addProduct(ProductDTO productdto) throws ResourceNotFoundException {
		ProductEntity product = this.dtoToEntity(productdto);
		ProductEntity saved = productrepo.save(product);
		return modelmapper.map(saved, ProductDTO.class);
	}

	@Override
	public ProductDTO updateProduct(int productId, ProductDTO productdto) throws ResourceNotFoundException{
		ProductEntity product = this.productrepo.findById(productId).
				orElseThrow(()-> new ResourceNotFoundException("Could not find Product with ID: " + productId));
		product.setDescription(productdto.getDescription());
		product.setProduct_name(productdto.getProduct_name());
		product.setPrice(productdto.getPrice());
		product.setImageUrl(productdto.getImageUrl());
		product.setQuantity(productdto.getQuantity());
		
		CategoryEntity category = categoryrepo.findById(productdto.getCategory_id()).
						orElseThrow(()->new ResourceNotFoundException("Could not find Category with ID: " + productdto.getCategory_id()));
		product.setCategory(category);
		ProductEntity saved = this.productrepo.save(product);
		return modelmapper.map(saved, ProductDTO.class);
	}

	@Override
	public void deleteProduct(int productId) throws ResourceNotFoundException {
		ProductEntity product = this.productrepo.findById(productId).
				orElseThrow(()-> new ResourceNotFoundException("Could not find Product with ID :" + productId));
		productrepo.delete(product);
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<ProductEntity> products = productrepo.findAll();
		return products.stream().map((product)-> this.entityToDTO(product)).collect(Collectors.toList());
	}
	
	private ProductDTO entityToDTO(ProductEntity product) {
		ProductDTO dto = new ProductDTO();
		dto.setDescription(product.getDescription());
		dto.setImageUrl(product.getImageUrl());
		dto.setPrice(product.getPrice());
		dto.setProduct_name(product.getProduct_name());
		dto.setCategory_id(product.getCategory().getCategoryId());
		return dto;
	}
	
	 private ProductEntity dtoToEntity(ProductDTO productDTO) throws ResourceNotFoundException {
	        ProductEntity product = new ProductEntity();
	        product.setProduct_name(productDTO.getProduct_name());
	        product.setDescription(productDTO.getDescription());
	        product.setPrice(productDTO.getPrice());
	        product.setQuantity(productDTO.getQuantity());
	        product.setImageUrl(productDTO.getImageUrl());

	        CategoryEntity category = categoryrepo.findById(productDTO.getCategory_id())
	                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	        product.setCategory(category);

	        return product;
	    }

}
