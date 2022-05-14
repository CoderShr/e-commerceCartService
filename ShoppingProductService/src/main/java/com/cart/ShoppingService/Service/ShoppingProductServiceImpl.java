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
public class ShoppingProductServiceImpl implements ShoppingProductService {
	
	@Autowired
	public ProductRepository productRepository;

	public List<Product> getProducts() throws ProductNotFoundException, Exception {
	    log.info("Get all products - started to find products from repo");
	    try{
	    return productRepository.findAll();
	    } catch(ProductNotFoundException productNotFoundException ){
	        log.error("Exception thrown, occured due to Products not found");
	        throw new productNotFoundException(productNotFoundException.getMessage()) ;
	    } catch(Exception e){
		log.error("Some exception occured while fetch product by Id");
		throw new Exception(e.getMessage());
	}
	
	public Product getProductById(Integer productId) throws ProductNotFoundException, Exception{
	    try {
		log.info("Find product by id: {}, started to find product from repo", productId);
	        Optional<Product> productDto = productRepository.findById(productId);
	    	if(!productDto.isPresent()) {
		    throw new ProductNotFoundException("product not found");
	    	}catch(ProductNotFoundException productNotFoundException ){
	            log.error("Product with product id = {} not found", productId);
	            throw new productNotFoundException(productNotFoundException.getMessage()) ;
	    	} catch(Exception e){
		    log.error("Some exception occured while fetch product by Id");
		    throw new Exception(e.getMessage());
		}
	    return productDto.get();
		
	}

	public Product getProductsByName(String productName) throws ProductNotFoundException, Exception {
		try { 
		    log.info("Find product by name: {}, started to find product from repo", productName);
		    Optional<Product> product = productRepository.findByProductName(productName);
		    return getProduct(product);
	    	}catch(ProductNotFoundException productNotFoundException ){
		    log.error("Product not found exception occured while fetch product by name : {}", productName);
	            throw new productNotFoundException(productNotFoundException.getMessage()) ;
	    	} catch(Exception e){
		    log.error("Some exception occured while fetch product by productName");
		    throw new Exception(e.getMessage());
		}
	}

	public List<Product> getProductsByCatagory(String productCatagory) throws ProductNotFoundException, Exception {
	    try {	
		log.info("Find product by catagory : {}, started to find product from repo", productCatagory);
		Optional<List<Product>> productList = productRepository.findByCatagory(productCatagory);
		if(!productList.isPresent()) {
			throw new Exception("product not found");
		}
	    } catch(ProductNotFoundException productNotFoundException ){
		log.error("Product not found exception occured while fetch product by catagory");
	        throw new productNotFoundException(productNotFoundException.getMessage()) ;
	    } catch(Exception e){
		log.error("Some exception occured while fetch product by catagory");
	        throw new Exception(e.getMessage());
	    }
	    return productList.get();
	}
	
	private Product getProduct(Optional<Product> product) throws Exception {
		if(!product.isPresent()) {
			throw new ProductNotFoundException("product not found");
		}
		return product.get();
	}
	
}
