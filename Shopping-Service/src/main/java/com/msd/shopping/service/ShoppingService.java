package com.msd.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.msd.shopping.dto.ProductDetails;
import com.msd.shopping.dto.ProductDto;
import com.msd.shopping.exception.ResourceNotFoundException;
import com.msd.shopping.exception.SomethingWentWrongException;
import com.msd.shopping.feign.cart.ProxyCart;
import com.msd.shopping.feign.customer.ProxyCustomer;
import com.msd.shopping.feign.inventory.ProxyInventory;
import com.msd.shopping.feign.order.ProxyOrder;
import com.msd.shopping.feign.product.ProxyProduct;
import com.msd.shopping.model.Cart;
import com.msd.shopping.model.Customer;
import com.msd.shopping.model.CustomerCart;
import com.msd.shopping.model.CustomerOrder;
import com.msd.shopping.model.Inventory;
import com.msd.shopping.model.LineItem;
import com.msd.shopping.model.Order;
import com.msd.shopping.model.Product;
import com.msd.shopping.repository.CustomerCartRepo;
import com.msd.shopping.repository.CustomerOrderRepo;

@Service
public class ShoppingService {

	@Autowired
	private ProxyProduct productFeign;
	
	@Autowired
	private ProxyCustomer customerFeign;
	
	@Autowired
	private ProxyCart cartFeign;
	
	@Autowired
	private ProxyInventory inventoryFeign;
	
	@Autowired
	private ProxyOrder orderFeign;
	
	@Autowired
	private CustomerCartRepo customerCartRepo;
	
	@Autowired
	private CustomerOrderRepo customerOrderRepo;
	
	@Autowired 
	private KafkaTemplate<String,Inventory> kafkaTemplate;
	String kafkaTopic ="Inventory-Service";
	
	/**
	 * Add Product
	 * @param productDetails
	 * @return success message and 202 HttpStatus
	 */
	public String addProductDetails(ProductDetails productDetails) {
      ProductDto productDto=new ProductDto();
		
		Product product=new Product();
		
		product.setProductDescription(productDetails.getProductDescription());
		product.setProductName(productDetails.getProductName());
		product.setProductPrice(productDetails.getProductPrice());
		productDto.setProduct(product);
		
		productFeign.addProduct(productDto);
		
		
		Inventory inventory =new Inventory ();
		
		Product prod= Optional.ofNullable(productFeign.getProductByName(productDetails.getProductName()).getBody())
				.orElseThrow(()->new SomethingWentWrongException("Something Went Wrong While Finding Product"));
		
		inventory.setProductId(prod.getId());
		inventory.setQuantity(productDetails.getQuantity());
		
		
		sentInventory(inventory);
		
		
		return " PRODUCT ADDED SUCCESSFULLY";
	}

	private void sentInventory(Inventory inventory) {
		
		kafkaTemplate.send(kafkaTopic,inventory);
	}
	
	/**
	 * Add New Customer and Create Empty Cart
	 * @param customer
	 * @return success message and 200 HttpStatus
	 */
	public ResponseEntity<Customer> addCustomer(Customer customer){
		
		Customer newCustomer = customerFeign.addCustomer(customer).getBody();
		if(newCustomer==null) throw new SomethingWentWrongException("Something Went Wrong While Creating Customer");
		
		Cart cart = new Cart();
		Cart newCart = cartFeign.addCart(cart).getBody();
		if(newCart==null) throw new SomethingWentWrongException("Something Went Wrong While Creating Cart");
		
		CustomerCart customerCart = new CustomerCart();
		customerCart.setCustomerId(newCustomer.getCustomerId());
		customerCart.setCartId(newCart.getCartId());
		customerCartRepo.save(customerCart);
			
			
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update Cart
	 * @param customerId
	 * @param cart
	 * @return updated cart and 200 HttpStatus
	 */
	public ResponseEntity<Cart> updateCart(String customerId, Cart cart){
		
		CustomerCart customerCart = Optional.ofNullable(customerCartRepo.findByCustomerId(customerId))
				.orElseThrow(()-> new ResourceNotFoundException("Cart Not Found For Customer Id "+customerId));
		
		Cart updatedCart = cartFeign.updateCart(customerCart.getCartId(), cart).getBody();
		
		return new ResponseEntity<>(updatedCart, HttpStatus.OK);
		
	}
	
	/**
	 * Add Order
	 * @param customerId
	 * @return order and 200 HttpStatus
	 */
	public ResponseEntity<Order> addOrder(String customerId){
		
		CustomerCart customerCart = Optional.ofNullable(customerCartRepo.findByCustomerId(customerId))
				.orElseThrow(()-> new ResourceNotFoundException("Cart Not Found For Customer Id "+customerId));
		
		Cart cart = Optional.ofNullable(cartFeign.searchCart(customerCart.getCartId()).getBody())
				.orElseThrow(()->new SomethingWentWrongException("Sorry, Cart is Empty !!"));
		
		if(cart.getLineItems().isEmpty()) throw new SomethingWentWrongException("Sorry, Cart is Empty !!");
		
		Order order = new Order();
		order.setLineItems(cart.getLineItems());
		Order savedOrder = Optional.ofNullable(orderFeign.addOrder(order).getBody())
				.orElseThrow(()->new SomethingWentWrongException("Something Went Wrong While Adding Order"));
		
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomerId(customerId);
		customerOrder.setOrderId(savedOrder.getOrderId());
		customerOrderRepo.save(customerOrder);
				
		updateProductInventories(savedOrder);
		
		Order checkOrder = Optional.ofNullable(orderFeign.searchOrder(savedOrder.getOrderId()).getBody()).orElseThrow(()->new SomethingWentWrongException("Something Went Wrong"));
		if(checkOrder.getLineItems().equals(cart.getLineItems())) {
			Cart emptyCart = new Cart();
			emptyCart.setCartId(customerCart.getCartId());
			cartFeign.updateCart(customerCart.getCartId(), emptyCart);
		}
		
		return new ResponseEntity<>(savedOrder, HttpStatus.OK);
	}
	
	//Method to update Inventory
	public void updateProductInventories(Order savedOrder){
		
		List<Long> productIdsList = savedOrder.getLineItems().stream().map(LineItem::getProductId).toList();
		List<Long> orderQtyList = savedOrder.getLineItems().stream().map(LineItem::getQuantity).toList();
		
		for(int i=0; i<savedOrder.getLineItems().size();i++) {
		
			Inventory inventory = Optional.ofNullable(inventoryFeign.getByProductId(productIdsList.get(i)).getBody())
					.orElseThrow(()->new SomethingWentWrongException("Something Went Wrong"));
		
			Inventory updatedInventory = new Inventory();
			updatedInventory.setProductId(inventory.getProductId());
			updatedInventory.setQuantity(inventory.getQuantity() - orderQtyList.get(i));
			
			inventoryFeign.updateInventory(productIdsList.get(i), updatedInventory);
		}
	}
	
	
	/**
	 * Orders
	 * @param customerId
	 * @return Orders and 200 HttpStatus
	 */
	public ResponseEntity<Object> orders(String customerId){
		
		CustomerOrder customerOrder = Optional.ofNullable(customerOrderRepo.findByCustomerId(customerId))
					.orElseThrow(()->new ResourceNotFoundException("Order Details Not Found"));
		
		Order order = orderFeign.searchOrder(customerOrder.getOrderId()).getBody();
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
