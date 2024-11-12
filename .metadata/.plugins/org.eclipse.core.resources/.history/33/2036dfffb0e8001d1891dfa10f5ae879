package com.msd.order.controller;

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

import com.msd.order.dto.OrderDto;
import com.msd.order.model.Order;
import com.msd.order.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody OrderDto orderDto){
		
		Order savedOrder = orderService.addOrder(orderDto.DtoToOrder());
		return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable String id){
		orderService.deleteOrder(id);
		return new ResponseEntity<>("Order is Deleted", HttpStatus.OK);
	}
	
	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody OrderDto orderDto){
		Order updatedOrder = orderService.updateOrder(id, orderDto.DtoToOrder());
		return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> searchOrder(@PathVariable String id){
		Order order = orderService.searchOrder(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

}
