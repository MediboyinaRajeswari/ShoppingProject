package com.msd.shopping.model;




import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	
	@Id
	private String id;
	private long productId;
	private long quantity;
}