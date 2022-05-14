package com.cart.ShoppingService.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name="user_profiles")
public class User {

	@Id
	private Integer id;
	
	private String name;
	
	private String email;
}
