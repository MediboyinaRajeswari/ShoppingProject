package com.msd.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.inventory.dto.InventoryDTO;
import com.msd.inventory.model.Inventory;
import com.msd.inventory.service.InventoryService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Inventory Controller
 * 
 * @author NA20379491
 * @version 3.0.5
 */

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private static Logger log= LoggerFactory.getLogger(InventoryController.class);
	
	    @Autowired
	    private InventoryService inventoryService;


		/**
		 * Method Name:addInventory, This method is used for add new Inventory
		 * 
		 * @param inventoryDto This is parameter to this method
		 * @return product,201 HTTP code
		 * @see InventoryController
		 */
	 
	    @PostMapping("/add")
	    public ResponseEntity<Inventory> addInventory(@RequestBody InventoryDTO inventoryDto) {
	        Inventory newInventory = inventoryService.addInventory(inventoryDto.getInventory());
	        log.info("Addded Succesfully");
	        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
	    }
	 
	 /**
		 * Method Name:updateInventory, This method is used for update the Inventory
		 * 
		 * @param  productId This is parameter1 to this method
		 * @param  updatedInventory This is parameter2 to this method
		 * @return product and 201 HTTP code
		 * @see InventoryController
		 */
	 
	 @PutMapping("/update/{productId}")
	    public ResponseEntity<Inventory> updateInventory(@PathVariable Long productId, @RequestBody Inventory updatedInventory) {
	      Inventory updated = inventoryService.updateInventory(productId, updatedInventory);
	      log.info("updated Succesfully");
			return new ResponseEntity<>(updated,HttpStatus.CREATED);
	       
		 
	 }
	   

		/**
		 * Method Name:getByproductId  ,This method is used for find product by
		 * using productId
		 * 
		 * @param productId This is parameter to this method
		 * @return Inventory and 200 HTTP code
		 * @see InventoryController
		 * 
		 */
	 
	 @GetMapping("/get/{productId}")
	    public ResponseEntity<Inventory> getByProductId(@PathVariable Long productId) {
	        Inventory inventory = inventoryService.getInventory(productId);
	        log.info("got Succesfully with Id");
	        return new ResponseEntity<>(inventory,HttpStatus.OK);
	        
	        

	    }
	 
	 /**
		 * Method Name:getAll, This method is used for find all Inventory
		 * 
		 * @return inventory and 200 HTTP code
		 * @see InventoryController
		 */
	 
	 @GetMapping("/getAll")
	 public ResponseEntity<List<Inventory>> getAllInventory() {
		    List<Inventory> inventoryList = inventoryService.getAll();
		    log.info(" All Inventory List");
			return new ResponseEntity<>(inventoryList,HttpStatus.OK);
		   
		}
	 

		/**
		 * Method Name: deleteInventory, This method is used for delete the inventory
		 * by using productId
		 * 
		 * @param productId This is parameter to this method
		 * @return 202 HTTP code
		 * @see InventoryController
		 */
	 
	 @DeleteMapping("/{productId}")
	    public ResponseEntity<String> deleteInventory(@PathVariable("productId") Long productId) {
	        inventoryService.deleteInventory(productId);
	        return ResponseEntity.ok("Inventory item with ID " + productId + " has been deleted.");
	    }

		/**
		 * Method Name:deleteAll, This method is used for delete all inventory
		 * 
		 * @return 202 HTTP code
		 * @see InventoryController
		 */
	 
	 @DeleteMapping("/deleteAll")
	 public ResponseEntity<String> deleteAllInventory() {
		    inventoryService.deleteAll();
		    return ResponseEntity.ok("All inventory items deleted successfully");
		}
}
