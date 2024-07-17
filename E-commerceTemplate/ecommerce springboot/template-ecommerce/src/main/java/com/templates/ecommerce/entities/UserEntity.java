package com.templates.ecommerce.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Users")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer userId;

	    @Column(nullable = false, unique = true)
	    private String username;

	    @Column(nullable = false, unique = true)
	    private String email;
	    
	    @JsonIgnore
	    @Column(nullable = false)
	    private String password;

	    private String firstName;
	    private String lastName;
	    private String address;
	    private String city;
	    private String country;
	    private String postalCode;
	    private String phoneNumber;
	    
	    @OneToMany(mappedBy = "user_id")
	    private List<ReviewsEntity> reviews;

}
