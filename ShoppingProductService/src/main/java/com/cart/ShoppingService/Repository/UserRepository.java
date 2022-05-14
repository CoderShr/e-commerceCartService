package com.cart.ShoppingService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.ShoppingService.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findById(Integer userId);}
