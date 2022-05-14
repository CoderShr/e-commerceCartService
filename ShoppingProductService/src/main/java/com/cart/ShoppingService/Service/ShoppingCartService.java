package com.cart.ShoppingService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Dto.AddToCartDto;
import com.cart.ShoppingService.Dto.CartDto;
import com.cart.ShoppingService.Dto.CartItemDto;
import com.cart.ShoppingService.Dto.ProductDto;
import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Model.User;
import com.cart.ShoppingService.Repository.ProductRepository;
import com.cart.ShoppingService.Repository.ShoppingCartRepository;

@Service
public Interface ShoppingCartService {

	public Cart addProductToCart(AddToCartDto addToCartDto, Integer userId) throws Exception;
	
	public CartDto getCartItems(Integer userId);

	public String updateCartItem(AddToCartDto cartDto, Integer userId) throws Exception;

	public Boolean deleteCartItem(int userId,int productId) throws Exception ;
	  
    	public void deleteUserCartItems(int user) ;

	
	
}
