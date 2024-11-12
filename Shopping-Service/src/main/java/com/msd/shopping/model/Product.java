package com.msd.shopping.model;

import org.springframework.data.annotation.Id;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	
	

	@Id
	private long id;

	@NotBlank
	@Size(min = 3, max = 100)
	private String productName;
	@NotBlank
	@Size(min = 3, max = 200)
	private String productDescription;
	@NotBlank
	private double productPrice;
	
	
}
