package com.msd.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msd.cart.exception.ResourceNotFoundException;
import com.msd.cart.model.LineItem;
import com.msd.cart.repository.LineItemRepository;

/**
 * LineItem Service
 * @author MO20364776
 *
 */
@Service
public class LineItemService {

	@Autowired
	LineItemRepository lineItemRepo;

	/**
	 * Method Name: addLineItem, This method is used for adding new LineItem
	 * 
	 * @param lineItemData
	 * @return 201 HTTP code
	 */
	public ResponseEntity<Object> addLineItem(LineItem lineItemData) {
		lineItemRepo.save(lineItemData);
		return new ResponseEntity<>("LineItem is Added", HttpStatus.CREATED);
	}

	/**
	 * Method Name: searchLineItem, This method is used to search LineItem by id
	 * 
	 * @param id
	 * @return lineItem and 200 HTTP code
	 */
	public ResponseEntity<LineItem> searchLineItem(String id) {
		LineItem lineItem = lineItemRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LineItem Not Found for id: "+id));
		return new ResponseEntity<>(lineItem, HttpStatus.OK);
	}

	/**
	 * Method Name: deleteLineItem, This method is used to delete LineItem by id
	 * 
	 * @param id
	 * @return 202 HTTP code
	 */
	public void deleteLineItem(String id) {
		lineItemRepo.deleteById(id);
	}

	/**
	 * Method Name: updateLineItem, This method is used to update LineItem
	 * 
	 * @param id
	 * @param lineItemData
	 * @return 200 HTTP code
	 */
	public ResponseEntity<Object> updateLineItem(String id, LineItem lineItemData) {
		LineItem item = lineItemRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LineItem Not Found"));
		lineItemData.setItemId(item.getItemId());
		lineItemRepo.save(lineItemData);
		return new ResponseEntity<>("LineItem with id: " + id + " is Updated", HttpStatus.OK);
	}

	/**
	 * Method Name: listLineItem, This method is used to get all LineItems
	 * 
	 * @return list and 200 HTTP code
	 */
	public ResponseEntity<Object> listLineItem() {
		List<LineItem> list = Optional.ofNullable(lineItemRepo.findAll())
				.orElseThrow(() -> new ResourceNotFoundException("LineItem Not Found"));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
