package com.cart.ShoppingService.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cart.ShoppingService.Dto.AddToCartDto;
import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Cart, String> {

	public List<Cart> findAllByUser(User user);


	public void deleteAllByUserId(int userId);


	public Cart getById(Integer id);


	public boolean existsById(int id);


	public void deleteById(int id);


	public Optional<Cart> findByProductId(Integer productId);


	public boolean existsByProductId(int productId);


	public void deleteByProductId(int productId);

}
