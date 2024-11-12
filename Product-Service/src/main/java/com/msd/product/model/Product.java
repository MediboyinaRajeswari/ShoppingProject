package com.msd.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="Product")
public class Product {
	
	
	@Transient
    public static final String SEQUENCE_NAME = "db_sequence";

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
