package com.msd.order.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Oders")
public class Order {
	
	@Id
	private String orderId;
	
	private List<LineItem> lineItems;

}
