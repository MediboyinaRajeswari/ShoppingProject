package com.msd.inventory.IntegrationTest;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.msd.inventory.dto.InventoryDTO;
import com.msd.inventory.model.Inventory;
import com.msd.inventory.repository.InventoryRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
class InventoryIntegrationTesting {
	
	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@BeforeAll
	public static void init() {
		
		restTemplate = new RestTemplate();
	}

	@BeforeEach()
	public void beforeSetUp() {
		
		baseUrl = baseUrl + ":" + port + "/inventory";
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(1)
	@DisplayName("Test : add Inventory")
	void testaddInventory() {
		
		InventoryDTO inventoryDto = new InventoryDTO();
		
		Inventory inventory = new Inventory("1",87,1000);
		inventoryDto.setInventory(inventory);
		
		ResponseEntity<InventoryDTO> newInventory = restTemplate.postForEntity(baseUrl + "/add", inventoryDto,InventoryDTO.class);
		assertNotNull(newInventory);
		
		assertEquals(201,newInventory.getStatusCodeValue());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(2)
	@DisplayName("Test : Update By Product ID")
	void testupdateByProductId() {
		
		HttpHeaders headers = new HttpHeaders();
		Inventory inventory = new Inventory("1",87,60);
		HttpEntity<Inventory> httpEntity = new HttpEntity<Inventory>(inventory,headers);
		ResponseEntity<Inventory> responseEntity = restTemplate.exchange(baseUrl + "/update/" +87, HttpMethod.PUT,httpEntity,Inventory.class);
		assertEquals(201,responseEntity.getStatusCodeValue());
	}

	
	@SuppressWarnings("deprecation")
	@Test
	@Order(3)
	@DisplayName("Test : Get By Product ID")
	void testgetByProductId() {
		
		ResponseEntity<Inventory> responseEntity = restTemplate.getForEntity(baseUrl + "/get/"+87,Inventory.class);
		assertEquals(200,responseEntity.getStatusCodeValue());
		assertEquals(60,responseEntity.getBody().getQuantity());
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(4)
	@DisplayName("Test : Get All Inventory")
	void testgetAllInventory() {
		
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(baseUrl + "/getAll",List.class);
		assertNotNull(responseEntity);
		assertEquals(200,responseEntity.getStatusCodeValue());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(5)
	@DisplayName("Test : Delete By Product ID")
	void testdeleteByProductId() {
		
		HttpHeaders headers = new HttpHeaders();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		HttpEntity<String> httpEntity = new HttpEntity(null,headers);
		
		
			ResponseEntity<String> responseEntity =restTemplate.exchange(baseUrl + "/87",HttpMethod.DELETE,httpEntity,String.class);
	    assertEquals(200,responseEntity.getStatusCodeValue());
		
	}
	
	@Test
	@Order(6)
	@DisplayName("Test : Delete All")
	void test_DeleteAll() {
		
		inventoryRepository.deleteAll();
		assertEquals(0,inventoryRepository.count());
	}
}


