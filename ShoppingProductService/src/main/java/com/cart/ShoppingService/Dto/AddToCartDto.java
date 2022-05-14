package com.cart.ShoppingService.Dto;

@Data
@Builder
public class AddToCartDto {
    private Integer id;
    private Integer productId;
    private Integer quantity;
}
