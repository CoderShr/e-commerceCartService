package com.cart.ShoppingService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Repository.ProductRepository;

@Service
@Slf4j
@Transactional(Propagation=Propagation.REQUIRED, readOnly=false)
public class ShoppingProductService {
	
	@Autowired
	public ProductRepository productRepository;

	public List<Product> getProducts() ;
	
	public Product getProductById(Integer productId) throws Exception ;
	   
	public Product getProductsByName(String productName) throws Exception ;

	public List<Product> getProductsByCatagory(String productCatagory) throws Exception ;

	
}
