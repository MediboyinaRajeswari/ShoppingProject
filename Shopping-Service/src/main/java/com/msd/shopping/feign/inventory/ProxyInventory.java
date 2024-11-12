package com.msd.shopping.feign.inventory;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.msd.shopping.dto.InventoryDTO;
import com.msd.shopping.model.Inventory;



@FeignClient(value="Inventory-Service")
public interface ProxyInventory {

	
	@PostMapping("/api/inventory/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody InventoryDTO inventoryDto);
	
	@GetMapping("/api/inventory/getAll")
	public ResponseEntity<List<Inventory>> getAllInventory();
	
	@GetMapping("/api/inventory/get/{productId}")
    public ResponseEntity<Inventory> getByProductId(@PathVariable Long productId);
	
	@PutMapping("/api/inventory/update/{productId}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long productId, @RequestBody Inventory updatedInventory);
	
	@DeleteMapping("/api/inventory/{productId}")
    public ResponseEntity<String> deleteInventory(@PathVariable("productId") Long productId);
	
	@DeleteMapping("/api/inventory/deleteAll")
	public ResponseEntity<String> deleteAllInventory();
}
