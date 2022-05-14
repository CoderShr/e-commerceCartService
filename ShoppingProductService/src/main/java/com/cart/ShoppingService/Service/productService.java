package com.cart.ShoppingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Dto.ProductDto;
import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Repository.ProductRepository;

@Service
public class productService {
	
    @Autowired
    private ProductRepository productRepository;
	
	public void addProduct(ProductDto productDto) {
	    if(productDto.getCategory().equals("Book")) {
			 Book book = new Book();
			 book.setId(productDto.getId());
		     book.setProductName(productDto.getName());
		     book.setPrice(productDto.getPrice());
		     book.setCatagory(productDto.getCategory());
		     book.setAuthor(productDto.getBook().getAuthor());
		     book.setGenre(productDto.getBook().getGenre());
		     book.setPublications(productDto.getBook().getPublications());
		     productRepository.save(book);
		} else {
			Apparal apparal = new Apparal();
			apparal.setId(productDto.getId());
			apparal.setProductName(productDto.getName());
			apparal.setPrice(productDto.getPrice());
			apparal.setCatagory(productDto.getCategory());
			apparal.setBrand(productDto.getApparal().getBrand());
			apparal.setDesign(productDto.getApparal().getDesign());
			apparal.setType(productDto.getApparal().getType());
		    productRepository.save(apparal);
		}
	}
}
