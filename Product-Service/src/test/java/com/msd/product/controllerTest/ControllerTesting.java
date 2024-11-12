package com.msd.product.controllerTest;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msd.product.controller.ProductController;
import com.msd.product.dto.ProductDto;
import com.msd.product.model.Product;
import com.msd.product.service.ProductServiceInterface;

@WebMvcTest(value=ProductController.class)
 class ControllerTesting {

	@MockBean
	private ProductServiceInterface productService;
	
	@Autowired
	private MockMvc mockMvc;

	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	private Product product;
	
	@BeforeEach
	void init(){
	    product = new Product(1, "TeddyBear", "soft and light weight", 500);
		
	}
	
	@Test
	void getAllProducts() throws Exception {
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		when(productService.getAll()).thenReturn(products);
		this.mockMvc.perform(get("/api/products/getAll"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()",is(products.size())));

	}
	@Test
	void test_getProductById() throws Exception {
		
		when(productService.getProduct(anyLong())).thenReturn(product);
		this.mockMvc.perform(get("/api/products/get/{productId}",1)).andExpect(status().isOk());
				
		
	}
	@Test
	void test_getProductByName() throws Exception {
		
		when(productService.getProductByName("TeddyBear")).thenReturn(product);
		this.mockMvc.perform(get("/api/products/getProduct/{productName}","TeddyBear")).andExpect(status().isOk());
				
	}
	
	@Test
	void test_getProductsByPriceRange() throws Exception {
		List<Product> products= new ArrayList<>();
		products.add(product);
		when(productService.findByProductPriceBetween(1L, 1200L)).thenReturn(products);
		this.mockMvc.perform(get("/api/products/get/{productMin}/To/{productMax}",1L,1200L)).andExpect(status().isOk());
				
	}
	@Test
	void test_deleteProductById() throws Exception {
		
		doNothing().when(productService).deleteProduct(anyLong());
		this.mockMvc.perform(delete("/api/products/delete/{productId}",1))
		.andExpect(status().isAccepted());				
	}
	@Test
	void test_updateProduct() throws Exception {
		
		product.setProductName("pink Teddy Bear");
		when(productService.updateProduct(1,product)).thenReturn(product);
		this.mockMvc.perform(put("/api/products/update/{productId}",1)
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(product)))
		.andExpect(status().isCreated());
	}
	@Test
	void test_addProduct() throws Exception {
		ProductDto productDto=new ProductDto();
		productDto.setProduct(product);
		when(productService.addProduct(productDto)).thenReturn(product);
		this.mockMvc.perform(post("/api/products/addProduct")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(product)))
		.andExpect(status().isCreated());
	}
	
	@Test
	void test_deleteAllProducts() throws Exception {
		
		doNothing().when(productService).deleteAll();
		this.mockMvc.perform(delete("/api/products/deleteAll"))
		.andExpect(status().isAccepted());				
	}

}
