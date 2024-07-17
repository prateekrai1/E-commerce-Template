package com.templates.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.templates.ecommerce.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

}
