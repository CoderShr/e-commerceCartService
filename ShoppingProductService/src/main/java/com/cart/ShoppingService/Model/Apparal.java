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
@Table(name="apparal")
@AttributeOverrides({
    @AttributeOverride(name="productId", column=@Column(name="PRODUCTID")),
    @AttributeOverride(name="productName", column=@Column(name="PRODCUCTNAME")),
    @AttributeOverride(name="price", column=@Column(name="PRICE"))
})
public class Apparal extends Product{

	private String type;
	private String brand;
	private String design;
	
	public Apparal(Apparal apparal, ProductDto productDto) {
		// TODO Auto-generated constructor stub
		super(productDto.getId(), productDto.getName(), productDto.getPrice());
		this.brand = apparal.getBrand();
		this.design = apparal.getDesign();
		this.type = apparal.getType();
	}
}
