package com.msd.order.dto;

import java.util.List;

import com.msd.order.model.LineItem;
import com.msd.order.model.Order;

import lombok.Data;

@Data
public class OrderDto {
	
	private String orderId;
	
	private List<LineItem> lineItems;
	
	public Order DtoToOrder() {
		Order order = new Order();
		order.setOrderId(this.orderId);
		order.setLineItems(this.lineItems);
		return order;
	}

}
