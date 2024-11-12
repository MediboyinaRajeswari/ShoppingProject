package com.msd.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msd.order.exceptions.ResourceNotFoundException;
import com.msd.order.exceptions.SomethingWentWrongException;
import com.msd.order.model.Order;
import com.msd.order.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public Order addOrder(Order order) {
		if(order.getLineItems()==null) throw new SomethingWentWrongException("Line Items can not be Empty");
		
		return orderRepository.save(order);
		
	}
	
	public void deleteOrder(String orderId) {
		if(orderRepository.existsById(orderId))
			orderRepository.deleteById(orderId);
		else {
			throw new ResourceNotFoundException("Order Not Found For Id: "+orderId);
		}
	}
	
	public Order updateOrder(String orderId, Order order) {
		
		Order savedOrder = orderRepository.findById(orderId)
				.orElseThrow(()->new ResourceNotFoundException("Order Not Exists For Id: "+orderId));
		
		savedOrder.setLineItems(order.getLineItems());
		return orderRepository.save(savedOrder);
	}
	
	public Order searchOrder(String orderId) {
		
		return orderRepository.findById(orderId)
				.orElseThrow(()->new ResourceNotFoundException("Order Not Found For Id: "+orderId));
	}

}
