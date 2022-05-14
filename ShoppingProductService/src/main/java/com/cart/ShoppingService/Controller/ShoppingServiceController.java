package com.cart.ShoppingService.Controller;

import java.util.Objects;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cart.ShoppingService.Dto.AddToCartDto;
import com.cart.ShoppingService.Dto.CartDto;
import com.cart.ShoppingService.Model.Cart;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Model.User;
import com.cart.ShoppingService.Service.ShoppingCartService;
import com.cart.ShoppingService.Service.UserService;

import io.swagger.annotations.ApiResponse;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;


@RestController
@RequestMapping("/v1/cart")
@AllArgsConstructor
@Slf4j
@Validated
public class ShoppingServiceController {
	
    @Autowired
    public ShoppingCartService shoppingCartService;
	 
    // Home Page
    @GetMapping("/")
    public String welcome()
    {
        return "<html><body>"
            + "<h1>WELCOME</h1>"
            + "</body></html>";
    }
  
	
	// Add product to cart
    @PostMapping("/add")
    public ResponseEntity<Cart> addToUserCart(@Valid @RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("userId") @Min(Value=1, message ="User id can't be 0 or negetive.") Integer userId) throws Exception {
	log.info("Request to add product : {} to cart started", addToCartDto.toString());
        Cart cart = shoppingCartService.addProductToCart(addToCartDto, userId);
	log.info("Product been added to cart succesfully");
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);

    }
    
    // to list cart items
    @GetMapping("/show/userCart")
    public ResponseEntity<CartDto> showUserCart(@RequestParam("userId") @Min(Value=1, message ="User id can't be 0 or negetive.") Integer userId)  {
	log.info("Request to show user cart items for user id : {}", userId);
        CartDto cart = shoppingCartService.getCartItems(userId);
	log.info("Retrieved user cart item successful for userId : {}" , userId);
        return new ResponseEntity<CartDto>(cart,HttpStatus.OK);
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateUserCart(@RequestBody 
    		@Valid AddToCartDto cartDto, @RequestParam("userId") @Min(Value=1, message ="User id can't be 0 or negetive.") Integer userId) throws Exception{ 
	log.info("Request to update cart product item :{}", addToCartDto.toString());
        String cart = shoppingCartService.updateCartItem(cartDto, userId);
	log.info("Update cart product item successful");
        return new ResponseEntity<String>(cart,HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Boolean> deleteCartItem(@RequestParam("userId") Integer @Min(Value=1, message ="User id can't be 0 or negetive.") userId, 
							@RequestParam("productId") @Min(Value=1, message ="User id can't be 0 or negetive.") Integer productId) throws Exception{
        log.info("Request to Delete cart product item started for product id ={}, user id ={}", productId, userId)
	Boolean deleted = shoppingCartService.deleteCartItem(userId, productId);
	log.info("deleted product with product id ={} from cart of user with user id ={}, productId, userId);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
    }
    
    //delete all cart item
    @DeleteMapping("/deleteAllCartItems")
    public ResponseEntity<String> deleteAllCartItems(@RequestParam("userId") @Min(Value=1, message ="User id can't be 0 or negetive.") Integer userId) {
	log.info("Request to delete all cart items started for user id ={}", userId)
	shoppingCartService.deleteUserCartItems(userId);
	log.info("Deleted cart all items of user with user id ={}", userId)
	return new ResponseEntity<String>("deleted all cart items from cart",HttpStatus.OK);
	}
    
}
