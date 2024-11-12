package com.msd.cart.testController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.msd.cart.controller.CartController;
import com.msd.cart.dto.CartDTO;
import com.msd.cart.model.Cart;
import com.msd.cart.model.LineItem;
import com.msd.cart.repository.CartRepository;
import com.msd.cart.service.CartService;


@SpringBootTest(classes = com.msd.cart.CartServiceApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("CartControllerTest")
@ActiveProfiles("test")
 class TestCartController {
	
	@Mock
	CartService cartService;
	
	@Mock
	CartRepository cartRepository;

	@InjectMocks
	private CartController cartController;

	Cart cart=getCart();
	Cart savedCart = new Cart();

	@BeforeEach
	public void startCart() {
		savedCart = cartRepository.save(cart);
	}
	
	@AfterEach
	public void stopCart() {
		cartRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		assertThat(cartController).isNotNull();
	}

	@Test
	@Order(1)
	@DisplayName("Test-> Save Cart")
	void saveCartTest() {
		CartDTO cartDTO = new CartDTO();
		cartController.addCart(cartDTO);
		
		verify(cartService, times(1)).addCart(cartDTO.dtoToCart());
	}
	
	@Test
	@Order(2)
	@DisplayName("Test-> Search Cart By Id")
	void searchCartByIdTest() {
		cartController.searchCart("123456789");
		verify(cartService, times(1)).searchCart("123456789");
	}
	
	@Test
	@Order(3)
	@DisplayName("Test-> Update Cart By Id")
	void updateCartTest() {
		
		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartId(cart.getCartId());
		cartDTO.setLineItems(getLineItems());
		
		cartController.updateCart(cart.getCartId(), cartDTO);
		verify(cartService, times(1)).updateCart(cart.getCartId(), cartDTO.dtoToCart());
	}
	
	@Test
	@Order(4)
	@DisplayName("Test-> Get All Carts")
	void findAllCartsTest() {
		cartController.listCart();
		verify(cartService, times(1)).listCart();
	}
	
	@Test
	@Order(5)
	@DisplayName("Test-> Delete Cart By Id")
	void deleteCartTest() {
		cartController.emptyCart(cart.getCartId());
		verify(cartService, times(1)).emptyCart(cart.getCartId());
	}

	
	private Cart getCart() {
		Cart cart=new Cart();
		return cart;
		
	}
	
	private List<LineItem> getLineItems() {
		List<LineItem> list = new ArrayList<>();
		LineItem lineItem = new LineItem();
		lineItem.setProductId(1001L);
		lineItem.setProductName("Samsung Galaxy");
		lineItem.setQuantity(2L);
		lineItem.setPrice(100);
		list.add(lineItem);
		return list;
		
	}

}
