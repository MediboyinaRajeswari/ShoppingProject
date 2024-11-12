package com.msd.product.integrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.msd.product.dto.ProductDto;
import com.msd.product.model.Product;
import com.msd.product.repository.DbSequenceRepository;
import com.msd.product.repository.ProductRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
 class IntegrationTesting {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";


	private static RestTemplate restTemplate;
	
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private DbSequenceRepository sequenceRepository ;
	
	@BeforeAll
	public static void init() {
		
		restTemplate = new RestTemplate();
	}

	
	@BeforeEach
	public void beforeSetUp() {
		baseUrl = baseUrl + ":" + port + "/api/products";
	}
	@SuppressWarnings("deprecation")
	@Test
	@Order(1)
	void test_AddProduct() {

		ProductDto productDto = new ProductDto();
		Product product = new Product(1,"Fridge", "Double Door", 25000);
		productDto.setProduct(product);
		ResponseEntity<Product> responseEntity = restTemplate.postForEntity(baseUrl+"/addProduct",productDto, Product.class);
		assertNotNull(responseEntity);
		assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(product.getProductName(),responseEntity.getBody().getProductName());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(2)
	void test_getAllProduct() {

		@SuppressWarnings("rawtypes")
		ResponseEntity<List>responseEntity=restTemplate.getForEntity(baseUrl+"/getAll", List.class);
	    assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
     
	}
	@SuppressWarnings("deprecation")
	@Test
	@Order(3)
	void test_getProductById() {
		ResponseEntity<Product> responseEntity=restTemplate.getForEntity(baseUrl+"/get/"+1, Product.class);
		
		assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Fridge",responseEntity.getBody().getProductName());
	}

	@SuppressWarnings("deprecation")
	@Test
	@Order(4)
	void test_getProductByName() {
		ResponseEntity<Product> responseEntity=restTemplate.getForEntity(baseUrl+"/getProduct/"+"Fridge", Product.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Fridge",responseEntity.getBody().getProductName());
	}
	@SuppressWarnings("deprecation")
	@Test
	@Order(5)
	void test_updateProduct() {
		HttpHeaders headers = new HttpHeaders();
		ProductDto productDto = new ProductDto();
		Product product = new Product(1,"Fridge", "Double Door", 25000);
		productDto.setProduct(product);
		
	    HttpEntity<ProductDto> httpEntity = new HttpEntity<ProductDto>(productDto, headers);
	    ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(baseUrl+"/update/"+1,
                HttpMethod.PUT, httpEntity, ProductDto.class);

        assertEquals(201, responseEntity.getStatusCodeValue());
      
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(6)
	void test_getProductBypriceRange() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> responseEntity=restTemplate.getForEntity(baseUrl+"/get/"+10+"/To/"+30000, List.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1,responseEntity.getBody().size());
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(7)
	void test_deleteProductById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
	    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl+"/delete/"+1,
                HttpMethod.DELETE, httpEntity, String.class);

        assertEquals(202, responseEntity.getStatusCodeValue());
      
	}
	
	@Test
	@Order(8)
	void deleteAll() {
		productRepository.deleteAll();
		assertEquals(0,productRepository.count());
	}
	@Test
	@Order(9)
	void deleteAllSeq() {
		sequenceRepository.deleteAll();
		assertEquals(0,sequenceRepository.count());
	}
}
