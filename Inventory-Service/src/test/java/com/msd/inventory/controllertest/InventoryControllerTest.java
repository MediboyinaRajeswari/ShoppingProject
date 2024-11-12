package com.msd.inventory.controllertest;

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
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msd.inventory.controller.InventoryController;
import com.msd.inventory.dto.InventoryDTO;
import com.msd.inventory.model.Inventory;
import com.msd.inventory.service.InventoryService;

@WebMvcTest(value=InventoryController.class)

class InventoryControllerTest {
	
	@MockBean
    private InventoryService inventoryService;
    
	@InjectMocks
    private InventoryController inventoryController;

	@Autowired
	private MockMvc mockMvc;

	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Inventory inventory;
	
	@Test
	void testaddInventory() throws Exception {
		InventoryDTO inventoryDto=new InventoryDTO();
		inventoryDto.setInventory(inventory);;
		when(inventoryService.addInventory(inventoryDto.getInventory())).thenReturn(inventoryDto.getInventory());
		this.mockMvc.perform(post("/inventory/add")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(inventory)));
		//.andExpect(status().isCreated());
	}
	
	@Test
	void testUpdateInventory() throws Exception {
		//set up test data
		Inventory inventory = new Inventory();
		inventory.setQuantity(87);
		//Mock the inventory service
		when(inventoryService.updateInventory(67L,inventory)).thenReturn(inventory);
		//make the request and verify the response
		this.mockMvc.perform(put("/inventory/update/{productId}",67L)
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(inventory)));
		//.andExpect(status().isCreated());
	}
	
	@Test
	void testgetProductById() throws Exception {
		
		when(inventoryService.getInventory(anyLong())).thenReturn(inventory);
		this.mockMvc.perform(get("/inventory/get/{productId}",67L)).andExpect(status().isOk());
	}
	

	@Test
	void getAllInventory() throws Exception {
		List<Inventory> inventoryList = new ArrayList<>();
		inventoryList.add(inventory);
		when(inventoryService.getAll()).thenReturn(inventoryList);
		this.mockMvc.perform(get("/inventory/getAll"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()",is(inventoryList.size())));
	}
	
	@Test
	void testdeletetByProductId() throws Exception {
		
		doNothing().when(inventoryService).deleteInventory(anyLong());
		this.mockMvc.perform(delete("/inventory/delete/{productId}",1));
		//.andExpect(status().isAccepted());				
	}
	

	@Test
	void testdeleteAllInventory() throws Exception {
		
		doNothing().when(inventoryService).deleteAll();
		this.mockMvc.perform(delete("/inventory/deleteAll"));
		//.andExpect(status().isAccepted());				
	}
	
	
	
	
	
	
}

	
