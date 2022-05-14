package com.cart.ShoppingService.Model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.cart.ShoppingService.Dto.ProductDto;

@Entity
@Data
@Builder
@Table(name="book")
@AttributeOverrides({
    @AttributeOverride(name="productId", column=@Column(name="PRODUCTID")),
    @AttributeOverride(name="productName", column=@Column(name="PRODCUCTNAME")),
    @AttributeOverride(name="price", column=@Column(name="PRICE"))
})
public class Book extends Product{
	
	private String genre;
	private String author;
	private String publications;
	
	public Book(Book book, ProductDto productDto) {
		super(productDto.getId(), productDto.getName(), productDto.getPrice());
		this.genre = book.getGenre();
		this.author = book.getAuthor();
		this.publications = book.getPublications();
		// TODO Auto-generated constructor stub
	}

}
