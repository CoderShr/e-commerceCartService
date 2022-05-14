
package com.cart.ShoppingService.Service;

import org.junit.*;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cart.ShoppingService.Model.Apparal;
import com.cart.ShoppingService.Model.Book;
import com.cart.ShoppingService.Model.Product;
import com.cart.ShoppingService.Repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = TestConfiguration.class )
public class ShoppingProductServiceTest {

    @Mock
    ProductRepository productRepository;
 
    @InjectMocks
    ShoppingProductService shoppingProductService;

	@Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProducts() {
        System.out.println("testing get Products");
 
        when( productRepository.findAll()).thenReturn( getMockedProducts() );

        List<Product> result = shoppingProductService.getProducts();
     
        // Assert expected results
        Assert.assertNotNull( result );
        Assert.assertEquals( 6, result.size());
    }


     @Test
    public void testGetProductById() {
        System.out.println("testing get Products by productId");

        when(productRepository.findById(2)).thenReturn(getProduct());

        Product result = null;
		try {
			result = shoppingProductService.getProductById(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        // Assert expected results
        Assert.assertNotNull( result);
        Assert.assertEquals( "Book", result.getCatagory() );
     }

    @Test(expected = Exception.class)
    public void testGetProductByIdThrowException() {
        System.out.println("testing get Products by productId not found");

        when(productRepository.findById(21)).thenThrow(Exception.class);

		try {
			Product result = shoppingProductService.getProductById(21);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

  @Test
    public void testGetProductByName() {
        System.out.println("testing get Products by productname");

        when(productRepository.findByProductName("Meluha")).thenReturn(getProduct());

        Product result = null;
		try {
			result = shoppingProductService.getProductsByName("Meluha");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
 	// Assert expected results
        Assert.assertNotNull( result);
        Assert.assertEquals( "Book", result.getCatagory());

    }

    @Test
    public void testGetProductByCatagory() {
        System.out.println("testing get Products by product catagory");

        when(productRepository.findByCatagory("Book")).thenReturn(getBookCatagoryProducts());

        List<Product> result = null;
		try {
			result = shoppingProductService.getProductsByCatagory("Book");
		} catch (Exception e) {
			e.printStackTrace();
		}
     
	// Assert expected results
        Assert.assertNotNull( result);
        Assert.assertEquals( "Meluha", result.get(1).getProductName() );
        Assert.assertEquals( "Book", result.get(1).getCatagory() );

    }

    private List<Product> getMockedProducts(){
        List<Product> productList = new ArrayList<>();

        Book product1 = new Book();
	product1.setId(1);
	product1.setProductName("God of little things");
	product1.setPrice(560);
	product1.setCatagory("Book");
	product1.setPublications("DC Books");
	product1.setGenre("Fiction");
	product1.setAuthor("Arundadi roy");
	
	productList.add(product1);


	Book product2 = new Book();
	product2.setId(2);
	product2.setProductName("Meluha");
	product2.setPrice(450);
	product2.setCatagory("Book");
	product2.setPublications("SKM publications");
	product2.setGenre("Historical Fiction");
	product2.setAuthor("Amit");

	productList.add(product2);

 	Book product3 = new Book();
	product3.setId(3);
	product3.setProductName("you are beautiful");
	product3.setPrice(360);
	product3.setCatagory("Book");
	product3.setPublications("DC Book");
	product3.setGenre("Romcom");
	product3.setAuthor("preeti shinoy");

	productList.add(product3);

 	Apparal product4 = new Apparal();
	product4.setId(4);
	product4.setProductName("Mens casuals");
	product4.setPrice(760);
	product4.setCatagory("Apparal");
	product4.setDesign("Skin fit");
	product4.setType("Jeans");
	product4.setBrand("LP");

	productList.add(product4);

	Apparal product5 = new Apparal();
	product5.setId(5);
	product5.setProductName("Womans casuals");
	product5.setPrice(460);
	product5.setCatagory("Apparal");
	product5.setDesign("Pleated");
	product5.setType("Skirt");
	product5.setBrand("Lara");

	productList.add(product5);
	
	Apparal product6 = new Apparal();
	product6.setId(6);
	product6.setProductName("Womans party wear");
	product6.setPrice(860);
	product6.setCatagory("Apparal");
	product6.setDesign("Flared");
	product6.setType("Gown");
	product6.setBrand("vera moda");

	productList.add(product6);

	return productList;

    }

    public Optional<Product> getProduct(){
	Book product2 = new Book();
	product2.setId(2);
	product2.setProductName("Meluha");
	product2.setPrice(450);
	product2.setCatagory("Book");
	product2.setPublications("SKM publications");
	product2.setGenre("Historical Fiction");
	product2.setAuthor("Amit");
	
	return Optional.of(product2);
    }
    
    private Optional<List<Product>> getBookCatagoryProducts(){
        List<Product> productList = new ArrayList<>();

        Book product1 = new Book();
	product1.setId(1);
	product1.setProductName("God of little things");
	product1.setPrice(560);
	product1.setCatagory("Book");
	product1.setPublications("DC Books");
	product1.setGenre("Fiction");
	product1.setAuthor("Arundadi roy");
	
	productList.add(product1);


	Book product2 = new Book();
	product2.setId(2);
	product2.setProductName("Meluha");
	product2.setPrice(450);
	product2.setCatagory("Book");
	product2.setPublications("SKM publications");
	product2.setGenre("Historical Fiction");
	product2.setAuthor("Amit");

	productList.add(product2);

 	Book product3 = new Book();
	product3.setId(3);
	product3.setProductName("you are beautiful");
	product3.setPrice(360);
	product3.setCatagory("Book");
	product3.setPublications("DC Book");
	product3.setGenre("Romcom");
	product3.setAuthor("preeti shinoy");

	productList.add(product3);
    return Optional.of(productList);
    }
}