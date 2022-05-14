package com.cart.ShoppingService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.ShoppingService.Dto.AddToCartDto;
import com.cart.ShoppingService.Dto.CartDto;
import com.cart.ShoppingService.Dto.CartItemDto;
import com.cart.ShoppingService.Dto.ProductDto;
import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Model.User;
import com.cart.ShoppingService.Repository.ProductRepository;
import com.cart.ShoppingService.Repository.ShoppingCartRepository;

@Service
@slf4j
@Transactional(Propagation=Propagation.REQUIRED, readOnly=false)
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	public UserService userService;
	public ShoppingCartRepository shoppingCartRepository;
	public ProductRepository productRepository;
	public ShoppingProductService shoppingProductService;

	@Autowired
	public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
		ProductRepository productRepository, ShoppingProductService shoppingProductService,
			UserService userService) {
		this.shoppingCartRepository = shoppingCartRepository;
		this.productRepository = productRepository;
		this.userService = userService;
		this.shoppingProductService = shoppingProductService;
	}

	public Cart addProductToCart(AddToCartDto addToCartDto, Integer userId) throws UserNotFoundException, Exception{
		log.info("Product of id ={} adding to cart", addToCartDto.getProductId());
		User user = userService.getUser(userId);
		Cart cart = null;
		if(Objects.nonNull(user)) {
		    enrichCartAndSave(addToCartDto, user, cart);
		} else {
		    throw new UserNotFoundException("User not found");
			
		}
		log.info("Product successfully added to cart");
		return cart;
    }
	
	public CartDto getCartItems(Integer userId) {
		log.info("Fetching cart items of user of userid ={}, userId);
	        List<CartItemDto> cartItems = new ArrayList<>();
	        for (Cart cart:getCartItemsOfUser(userId)){   		
	            CartItemDto cartItemDto = new CartItemDto(cart);
	            cartItems.add(cartItemDto);
	        }
	        double totalCost = 0;
	        for (CartItemDto cartItemDto :cartItems){
	            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
	        }

		log.info("User cart items fetched");
	        return new CartDto(cartItems, totalCost);
			
	}

	@Transactional
	public Boolean deleteCartItem(int userId,int productId) throws ProductNotFoundException, Exception {
	    log.info("Product started to delete from userCart for product id={} and user id ={}", productId, userId);
            if (!findUserCartItemByProductId(productId, userId).isPresent()) {
                throw new ProductNotFoundException("Cart item doesnot exist exception for the product : " + productId);      
            }
            shoppingCartRepository.deleteByProductId(productId);

	    log.info("Product with id ={} deleted from cart", productId);
            return true;

        }

	@Transactional
    	public void deleteUserCartItems(int user) {
	    log.info("Started cto delete cart for user with userId", userId);
    	    shoppingCartRepository.deleteAllByUserId(user);
	    log.info("All user cart items are deleted for user iwth id={}", userId);
    	}


	public String updateCartItem(AddToCartDto cartDto, Integer userId) throws Exception {	
	    log.info("Product with id ={} deleted from cart", productId);			
            if(cartDto.getQuantity() == 0) {
        	return deleteIfZeroQuantity(cartDto, userId);
            } else {       	
                return updateCart(cartDto, userId);
       	    }
	}

	private String updateCart(AddToCartDto cartDto, Integer userId) throws ProductNotFoundException {
	    Optional<Cart> cart;
	    try {
 		cart = findUserCartItemByProductId(cartDto.getProductId(), userId);
	    } catch (Exception e) {
		throw new ProductNotFoundException("cart item not found for the product, failed to update");
	    }
            cart.get().setQuantity(cartDto.getQuantity());		
            shoppingCartRepository.save(cart.get());
	    log.info("Cart item updated");
            return "updated";
	}


	private String deleteIfZeroQuantity(AddToCartDto cartDto, Integer userId) throws ProductNotFoundException{
		try {
			deleteCartItem(cartDto.getProductId(), userId);
		} catch (Exception e) {
			throw new ProductNotFoundException("cart item not found for the product, failed to delete");
		}
		return "deleted";
	}

	private Optional<Cart> findUserCartItemByProductId(Integer productId, Integer userId) throws ProductNotFoundException{
		Optional<Cart> cart = getCartItemsOfUser(userId).stream()
        			.filter(cartItem -> cartItem.getProduct().getProductId().equals(productId)).findFirst();
        if(!cart.isPresent()){  
        	throw new ProductNotFoundException("product not found");
        }
		log.info("Product item deleted");
		return cart;
	}
	
	private List<Cart> getCartItemsOfUser(Integer userId) {
		List<Cart> cartList = shoppingCartRepository.findAllByUser(getUser(userId));
		return cartList;
	}

	private User getUser(Integer userId) {
		User user = userService.getUser(userId);
		return user;
	}
	

	private void enrichCartAndSave(AddToCartDto addToCartDto, User user, Cart cart) throws Exception {
		
		Optional<List<Cart>> cartExisting = Optional.ofNullable(shoppingCartRepository.findAllByUser(user));			
			Optional<Cart> cartItemExisting = cartExisting.isPresent() 
					? (cartExisting.get().stream()
					.filter(cartItem -> cartItem.getProduct().getProductId().equals(addToCartDto.getProductId())).findFirst())
					:(null);
			
			if(cartItemExisting.isPresent()) {
				cartItemExisting.get().setQuantity(addToCartDto.getQuantity());
				cart = cartItemExisting.get();
			}
			else {
				Product product = shoppingProductService.getProductById(addToCartDto.getProductId());
				cart = new Cart();
				cart.setProduct(product);
				cart.setUser(user);
				cart.setQuantity(addToCartDto.getQuantity());
		 	}			
			shoppingCartRepository.save(cart);
	}
}
