package com.cart.ShoppingService.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.cart.ShoppingService.Dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private	String productName;
	private float price;
	String catagory;
	//@JsonIgnore
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	//private List<Cart> carts;

	@ManyToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;

	public Product(Integer productId, String productName, float price) {
		this.id = productId;
		this.productName = productName;
		this.price = price;
	}

	public Product(ProductDto productDto, String category) {
		this.catagory = productDto.getCategory();
		this.id = productDto.getId();
		this.productName = productDto.getName();
		this.price = productDto.getPrice();
	}
}
