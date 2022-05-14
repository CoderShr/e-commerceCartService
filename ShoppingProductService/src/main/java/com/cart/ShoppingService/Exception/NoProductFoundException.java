package com.cart.ShoppingService.Exception;

public class NoProductFoundException extends RuntimeException {

    public NoProductFoundException() {

        super("No product found");
    }
}