package com.msd.product.controller;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.msd.product.dto.ProductDto;
import com.msd.product.model.Product;
import com.msd.product.service.ProductServiceInterface;

/**
 * Product Controller
 * 
 * @author ME20385435
 * @version 3.0.5
 * @since 2023-04-28
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {

	
   private static Logger log= LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductServiceInterface productService;
	
	
	/**
	 * Method Name:addProduct, This method is used for add new Product
	 * 
	 * @param productDto This is parameter to this method
	 * @return product,201 HTTP code
	 * @see ProductController
	 */
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
		Product product=productService.addProduct(productDto);
		log.info("Addded Succesfully");
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}
	
	
	/**
	 * Method Name:getProduct  ,This method is used for find product by
	 * using productId
	 * 
	 * @param productId This is parameter to this method
	 * @return product and 200 HTTP code
	 * @see ProductController
	 */
	
	@GetMapping("/get/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable long productId) {
		Product product=productService.getProduct(productId);
		log.info("got Succesfully with Id");
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	
	
	/**
	 * Method Name:getProductByName  , This method is used for find product
	 * by using productName
	 * 
	 * @param productName This is parameter to this method
	 * @return product and 200 HTTP code
	 * @see ProductController
	 */

	@GetMapping("/getProduct/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
		Product product=productService.getProductByName(productName);
		log.info("got Succesfully with Name");
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	/**
	 * Method Name:getProductsByPriceRange  , This method is used for find products
	 * by using price minimum to maximum
	 * 
	 * @param  productMin This is parameter1 to this method
	 * @param  productMax This is parameter2 to this method
	 * @return products and 200 HTTP code
	 * @see ProductController
	 */
	
	@GetMapping("/get/{productMin}/To/{productMax}")
	public ResponseEntity<List<Product>> getProductsByPriceRange(@PathVariable double productMin,@PathVariable double productMax) {
		List<Product> products=productService.findByProductPriceBetween(productMin, productMax);
		log.info("got Succesfully between Price Range");
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	/**
	 * Method Name:updateProduct, This method is used for update the Product
	 * 
	 * @param  productId This is parameter1 to this method
	 * @param  productDto This is parameter2 to this method
	 * @return product and 201 HTTP code
	 * @see ProductController
	 */
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable long productId,@RequestBody ProductDto productDto) {
		Product product=productService.updateProduct(productId,productDto.getProduct());
		log.info("updated Succesfully");
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}
	
	
	/**
	 * Method Name: deleteProduct, This method is used for delete the Product
	 * by using productId
	 * 
	 * @param productId This is parameter to this method
	 * @return 202 HTTP code
	 * @see ProductController
	 */
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable long productId) {
		productService.deleteProduct(productId);
		log.info("deleted Succesfully");
		return new ResponseEntity<>("Deleted SuccessFully!!",HttpStatus.ACCEPTED);
	}
	
	/**
	 * Method Name:deleteAll, This method is used for delete all products
	 * 
	 * @return 202 HTTP code
	 * @see ProductController
	 */
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		productService.deleteAll();
		log.info("deleted All Products Succesfully");
		return new ResponseEntity<>("Deleted All Products!!",HttpStatus.ACCEPTED);
	}
	
	
	/**
	 * Method Name:getAll, This method is used for find all products
	 * 
	 * @return products and 200 HTTP code
	 * @see ProductController
	 */

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAll() {
		List<Product> products=	productService.getAll();
		log.info(" All Products List");
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	
}
