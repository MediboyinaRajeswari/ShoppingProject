package com.msd.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import com.msd.inventory.exception.ResourceNotFoundException;
import com.msd.inventory.model.Inventory;
import com.msd.inventory.repository.InventoryRepository;



/**
 * Inventory service
 * 
 * @author NA20379491
 * @version 3.0.5
 */

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	/**
	 * Method Name:addInventory, This method is used for add new Inventory
	 * 
	 * @return inventory
	 * @see InventoryService
	 */

	public Inventory addInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	/**
	 * Method Name:updateInventory, This method is used for update the Inventory
	 * 
	 * @param productId This is parameter1 to this method
	 * @param updatedInventory This is parameter2 to this method
	 * @return Inventory
	 * @see InventoryService
	 */

	public Inventory updateInventory(long productId, Inventory updatedInventory) {
		Inventory updateInventory = getInventory(productId);
		updateInventory.setProductId(updatedInventory.getProductId());
		updateInventory.setQuantity(updatedInventory.getQuantity());
		return inventoryRepository.save(updateInventory);
	}
	

	/**
	 * Method Name:getInventory  ,This method is used for find inventory by productId
	 * using productId
	 * 
	 * @param productId This is parameter to this method
	 * @exception ResourceNotFoundException if Product Not Exists
	 * @return inventory
	 * @see InventoryService
	 */
	

	public Inventory getInventory(Long productId) {
		Inventory inventory = inventoryRepository.findByProductId(productId);
		if (inventory == null) {
			throw new ResourceNotFoundException("product not found with id " + productId);
		}
		return inventory;
	}

	/**
	 * Method Name:getAll, This method is used for get all inventory
	 * 
	 * @exception ResourceNotFoundException if no products available
	 * @return inventory 
	 * @see InventoryService
	 */
	public List<Inventory> getAll() {
		List<Inventory> inventory = inventoryRepository.findAll();
		if (inventory.isEmpty()) {
			throw new ResourceNotFoundException("No products Available");
		}
		return inventory;
	}
	
	/**
	 * Method Name: deleteInventory, This method is used for delete the Product
	 * by using productId
	 * 
	 * @param productId This is parameter to this method
	 * @exception ResourceNotFoundException if Product Not Exists
	 * @see InventoryService
	 */

	public void deleteInventory(Long productId) {
		Inventory inventory = inventoryRepository.findByProductId(productId);
		if (inventory == null) {
			throw new ResourceNotFoundException("product not found with id " + productId);
		}
		inventoryRepository.delete(inventory);
	}
	
	/**
	 * Method Name:deleteAll, This method is used for delete all inventory
	 * @exception ResourceNotFoundException if no products available
	 *  @see InventoryService
	 */

	public void deleteAll() {
		List<Inventory> inventory = inventoryRepository.findAll();
		if (inventory.isEmpty()) {
			throw new ResourceNotFoundException("No products Available");
		}
		inventoryRepository.deleteAll();
	}
	
	@KafkaListener(topics="Inventory-Service",groupId="G2")
	public void consume(Inventory inventory) {
		inventoryRepository.save(inventory);
	}
	
	
	
}
	