package com.cart.ShoppingService.Dto;

import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.Product;

@Data
@Builder
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;
}
