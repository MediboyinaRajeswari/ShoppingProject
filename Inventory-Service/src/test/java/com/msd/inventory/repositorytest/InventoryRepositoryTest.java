package com.msd.inventory.repositorytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.msd.inventory.model.Inventory;
import com.msd.inventory.repository.InventoryRepository;
import java.util.List;

import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("InventoryRepositoryTest")
@ActiveProfiles("test")
@SpringBootTest
public class InventoryRepositoryTest {
	@Autowired
     private InventoryRepository inventoryRepository;
	 
	 Inventory inv=getInventory();
		
	private Inventory getInventory() {
		 Inventory inventory = new Inventory("1",57,87);
		 
			return inventory;
		}
	
	 @BeforeEach
		public void inventory() {
			inventoryRepository.save(inv);
		}

	   @Test
		@Order(1)
		@DisplayName("Test-> add Inventory")
		void testSave() {
			Inventory result = inventoryRepository.findByProductId(inv.getProductId());
			assertEquals(inv.getQuantity(),result.getQuantity());
		}
	 
	   @Test
		@Order(2)
		@DisplayName("Test-> Get Inventory By ProductId")
		void testGetInventory() {
			Inventory result = inventoryRepository.findByProductId(inv.getProductId());
			assertEquals(inv.getQuantity(), result.getQuantity());
		}
	   
	   @Test
		@Order(3)
		@DisplayName("Test-> Get All inventory")
		void testGetAllInventory() {
			List<Inventory> result = new ArrayList<Inventory>();
			inventoryRepository.findAll().forEach(e -> result.add(e));
			assertEquals(1, result.size());
		}
	   
	    @Test
		@Order(4)
		@DisplayName("Test-> Update Inventory")
		void testUpdate() {
			Inventory result = inventoryRepository.findByProductId(inv.getProductId());
			result.setProductId(inv.getProductId());
			result.setId(inv.getId());
			result.setQuantity(56);;
			inventoryRepository.save(result);
			assertEquals(56,result.getQuantity());
		}
	    
	    @Test
		@Order(5)
		@DisplayName("Test-> Delete By productId")
		void testdeleteByProductId() {
			Inventory result = inventoryRepository.findByProductId(inv.getProductId());
			
			inventoryRepository.delete(result);
			List<Inventory> result1 = new ArrayList<>();
			inventoryRepository.findAll().forEach(e -> result1.add(e));
			
			assertEquals(0, result1.size());
			
		}
		
		@Test
		@Order(6)
		@DisplayName("Test-> Delete ALL inventory ")
		void testDeleteAll() {
			inventoryRepository.deleteAll();
			List<Inventory> result = new ArrayList<>();
			inventoryRepository.findAll().forEach(e -> result.add(e));
			assertEquals(0, result.size());
		}
		
		
	 

}
