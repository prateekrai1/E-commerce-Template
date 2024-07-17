package com.templates.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.templates.ecommerce.entities.CartEntity;

public interface CartRepo extends JpaRepository<CartEntity, Integer>{

}
