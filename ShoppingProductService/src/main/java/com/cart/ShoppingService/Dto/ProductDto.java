package com.cart.ShoppingService.Dto;

import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Product;

@Data
@Builder
public class ProductDto {

    private  Integer id;
    private  String name;
    private  float price;
    private  String category;
    private Book book;
    private Apparal apparal;

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getProductName());
        this.setPrice(product.getPrice());
        this.setCategory(product.getCatagory());
    }
}
