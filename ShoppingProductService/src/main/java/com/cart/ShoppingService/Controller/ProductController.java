package com.cart.ShoppingService.Controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.ShoppingService.Dto.ProductDto;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Service.ShoppingProductService;
import com.cart.ShoppingService.Service.productService;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
@Slf4j
public class ProductController {
	
	@Autowired
	public ShoppingProductService shoppingProductService;
	
	@Autowired
	public productService productService;
	
	//show all products
	@GetMapping("/search")
	
    	public ResponseEntity<List<Product>> searchAllProducts() {
	    log.info("Request to search all product items");
            List<Product> body = shoppingProductService.getProducts().orElseThrow(() -> new ProductNotFoundException());;
	    log.info("All product item retrieved");
            return new ResponseEntity<List<Product>>(body, HttpStatus.OK);
   	}
	
	// search and show particular item by product ID
    	@GetMapping("/productsById/{productId}")
	public ResponseEntity<Product> showProductItemByID(@PathVariable(value = "productId") Integer productId) throws Exception {
    	    log.info("Request to search product item for product id ={} started", productId);
	    Product body = shoppingProductService.getProductById(productId).orElseThrow(() -> new ProductNotFoundException());;
	    log.info("Product item with product id ={} retrived, productId);
            return new ResponseEntity<Product>(body, HttpStatus.OK);
      
	}
    
    	// Get particular item by product category
   	@GetMapping("/productsByCatagory/{productCatagory}")
	public ResponseEntity<List<Product>> showProductItemByCatagory(@PathVariable String productCatagory) throws Exception {  
    	    log.info("Request to search product items for product Catagory={} started", productCatagory) ;
	    List<Product> body = shoppingProductService.getProductsByCatagory(productCatagory).orElseThrow(() -> new ProductNotFoundException());;     
    	    log.info("Product items with product Catagory={} retrieved", productCatagory) ;
	    return new ResponseEntity<List<Product>>(body, HttpStatus.OK);	        	
	}	
    
    
	// Get particular item by product name
	@GetMapping("/productsByName/{productName}")
	public ResponseEntity<Product> showProductItemByName(@PathVariable String productName) throws Exception {
	    log.info("Request to search product item for product name={} started", productName);
	    Product body = shoppingProductService.getProductsByName(productName).orElseThrow(() -> new ProductNotFoundException());;  
	    log.info("Product item with product name={} started", productName);  
   	    return new ResponseEntity<Product>(body, HttpStatus.OK);
	}
	

	//add products
    	@PostMapping("/add")
   	public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
	    log.info("Add product with product id ={}, productDto.getProductId());
            productService.addProduct(productDto);
	    log.info("Product added with product id ={}, productDto.getProductId());
            return new ResponseEntity<String>( "Product has been added", HttpStatus.CREATED);
    }
}
