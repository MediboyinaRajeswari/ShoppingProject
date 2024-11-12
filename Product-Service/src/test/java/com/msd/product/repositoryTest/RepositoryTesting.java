package com.msd.product.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.msd.product.ProductServiceApplication;
import com.msd.product.model.Product;
import com.msd.product.repository.ProductRepository;

@SpringBootTest(classes = ProductServiceApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ProductRepositoryTest")
@ActiveProfiles("test")
class RepositoryTesting {

	@Autowired
	private ProductRepository productRepository;
	
	Product product=getProduct();
	
	@BeforeEach
	public void product() {
		productRepository.save(product);
	}
	
	@Test
	@Order(1)
	@DisplayName("Test-> Save Product")
	void testSave() {
		Product result = productRepository.findById(product.getId()).get();
		assertEquals(product.getProductName(), result.getProductName());
	}
	
	@Test
	@Order(2)
	@DisplayName("Test-> Find Product By Id")
	void testFindById() {
		Product result = productRepository.findById(product.getId()).get();
		assertEquals(product.getId(), result.getId());
	}
	@Test
	@Order(3)
	@DisplayName("Test-> Find Product By Name")
	void testFindByName() {
		Product result = productRepository.findByProductName(product.getProductName());
		assertEquals(product.getProductName(), result.getProductName());
	}
	@Test
	@Order(4)
	@DisplayName("Test-> Update Product")
	void testUpdate() {
		Product result = productRepository.findById(product.getId()).get();
		result.setId(product.getId());
		result.setProductName("Remote Car");
		result.setProductDescription("Charging Car and multi colors available");
		productRepository.save(result);
		assertEquals("Charging Car and multi colors available",result.getProductDescription());
	}
	
	@Test
	@Order(5)
	@DisplayName("Test-> find Product by Using Price Range")
	void testPriceRange() {
		List<Product> products=productRepository.findByProductPriceBetween(35, 5000);
		assertEquals(1,products.size());
	}
	
	
	
	
	@Test
	@Order(6)
	@DisplayName("Test-> Get All products")
	void testFindAll() {
		List<Product> result = new ArrayList<Product>();
		productRepository.findAll().forEach(e -> result.add(e));
		assertEquals(1, result.size());
	}
	
	
	@Test
	@Order(7)
	@DisplayName("Test-> Delete By Id")
	void testDeleteById() {
		Product result = productRepository.findById(product.getId()).get();
		productRepository.deleteById(result.getId());
		List<Product> result1 = new ArrayList<>();
		productRepository.findAll().forEach(e -> result1.add(e));
		assertEquals(0, result1.size());
	}
	
	@Test
	@Order(8)
	@DisplayName("Test-> Delete ALL Products ")
	void testDeleteAll() {
		productRepository.deleteAll();
		List<Product> result = new ArrayList<>();
		productRepository.findAll().forEach(e -> result.add(e));
		assertEquals(0, result.size());
	}
	
	
	
	
	private Product getProduct() {
		Product product=new Product(1,"RemoteCar","charging car",4000);
		return product;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
