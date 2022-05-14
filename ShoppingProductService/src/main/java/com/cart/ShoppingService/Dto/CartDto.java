package com.cart.ShoppingService.Dto;

import java.util.List;

@Data
@Builder
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;
}