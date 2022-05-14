package com.cart.ShoppingService.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Builder
@Table(name="cart")
public class Cart{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@OneToOne(targetEntity=User.class)
	@JoinColumn(nullable=false,name="user_id")
	private User user;
	private int quantity;
	
	//@ManyToOne
	//@JoinColumn(name = "product_id", referencedColumnName = "id")
	//private Product product;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
	private List<Products> products;

}