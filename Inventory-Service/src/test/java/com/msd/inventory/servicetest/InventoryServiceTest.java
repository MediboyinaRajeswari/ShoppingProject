package com.msd.inventory.servicetest;
import java.util.List;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.msd.inventory.repository.InventoryRepository;
import com.msd.inventory.service.InventoryService;
import com.msd.inventory.model.Inventory;
import com.msd.inventory.dto.InventoryDTO;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class InventoryServiceTest {
	
    @Mock
    private InventoryRepository inventoryRepository;
    
	@InjectMocks
    private InventoryService inventoryService;

    @Test
    public void testaddInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(345);
        inventory.setQuantity(67);
        when(inventoryRepository.save(inventory)).thenReturn(inventory);
        assertEquals(inventory, inventoryService.addInventory(inventory));
    }
    
    @Test
    public void testgetAll() {
        // create some sample inventory items
        Inventory inventory1 = new Inventory("678",876,90);
        Inventory inventory2 = new Inventory("234",450,9);
        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory1);
        inventoryList.add(inventory2);
        
        // mock the inventoryRepository's findAll() method to return the sample inventory items
        Mockito.when(inventoryRepository.findAll()).thenReturn(inventoryList);
        
        // call the getAll() method of the inventoryService
        List<Inventory> returnedInventoryList = inventoryService.getAll();
        
        // verify that the returned inventory list is equal to the sample inventory list
        assertEquals(inventoryList, returnedInventoryList);
    }
    @Test
    public void testGetByProductId() {
        // create a sample inventory object and save it to the repository
        Inventory inventory = new Inventory();
        inventory.setProductId(345);
        inventory.setId("1");
        inventory.setQuantity(10);
        inventoryRepository.save(inventory);
        when(inventoryRepository.findByProductId(anyLong())).thenReturn((inventory));
        // call the getInventory method with the product id
        Inventory result = inventoryService.getInventory(inventory.getProductId());
        
        // check if the result matches the expected inventory object
        assertEquals(inventory, result);
    }
   
    @Test
    public void testUpdateInventory() {
		Inventory inventory =new Inventory("134",345L,30);
		when(inventoryRepository.findByProductId(anyLong())).thenReturn((inventory));
		inventory.setQuantity(780);
		Inventory update= inventoryService.updateInventory(345L, inventory);
		
		assertEquals(780,update.getQuantity());
		
	}
    
    @Test
	
	public void testdeleteByProductId() {

		Inventory inventory=new Inventory("123",345L,89);

		InventoryDTO inventoryDto=new InventoryDTO();
		inventoryDto .setInventory(inventory);
		when(inventoryRepository.findByProductId(anyLong())).thenReturn((inventory));

		inventoryService.deleteInventory(345L);

		verify(inventoryRepository, times(1)).delete(inventory);
		assertEquals(0, inventoryRepository.count());
	}
    
    @Test
    public void testdeleteAll() {

		Inventory inventory1=new Inventory("145",267,88);
		Inventory inventory2=new Inventory("145",564,76);
		List<Inventory> inventory= new ArrayList<>();
		inventory.add(inventory1);
		inventory.add(inventory2);
		when(inventoryRepository.findAll()).thenReturn(inventory);

		inventoryService.deleteAll();

		verify(inventoryRepository, times(1)).deleteAll();
		assertEquals(0,inventoryRepository.count());
	}

   



    

}

