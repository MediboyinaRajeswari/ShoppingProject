package com.msd.product.serviceTest;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import com.msd.product.dto.ProductDto;

import com.msd.product.model.Product;
import com.msd.product.repository.ProductRepository;
import com.msd.product.service.ProductService;
import com.msd.product.service.SequenceGeneratorService;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest

 class ServiceTesting {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private  ProductService  productService;
	
	
	@InjectMocks
	private SequenceGeneratorService serviceSequence;
	
	
	@Test
	@Order(1)
	void test_getAllProducts() {

		Product product1=new Product(1,"hairClips","thin and large size clips",45);

		Product product2=new Product(2,"hairBand","Red color ",50);
		List<Product> products= new ArrayList<>();
		products.add(product1);
		products.add(product2);

		when(productRepository.findAll()).thenReturn(products);


		assertEquals(products.size(), productService.getAll().size());

	}

	@Test
	@Order(2)
	void test_getproductByproductId() {

		Product product1=new Product(1,"hairClips","thin and large size clips",45);

		Product product2=new Product(2,"hairBand","Red color ",50);
		List<Product> products= new ArrayList<>();
		products.add(product1);
		products.add(product2);

		when(productRepository.findById(anyLong())).thenReturn(Optional.of(products.get(0)));

		assertEquals(1, productService.getProduct(1).getId());

	}

	@Test
	@Order(3)
	void test_getProductByProductName() {

		Product product1=new Product(1,"hairClips","thin and large size clips",45);

		Product product2=new Product(2,"hairBand","Red color ",50);
		List<Product> products= new ArrayList<>();
		products.add(product1);
		products.add(product2);
		String name = "hairBand";

		when(productRepository.findByProductName(name)).thenReturn(product2);
		Product  product=productService.getProductByName(name);

		assertEquals(product2.getProductName(), product.getProductName());

	}

	

	@Test
	@Order(5)
	void test_updateProduct() {
		Product product =new Product(1,"NailPolish","RedColor",30);
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
		product.setProductName("Nail Remover");
		Product update= productService.updateProduct(1, product);
		
		assertEquals("Nail Remover",update.getProductName());
	}

	@Test
	@Order(6)
	void test_getProductByPriceRange() {
		
		Product product1=new Product(1,"hairClips","thin and large size clips",45);

		Product product2=new Product(2,"hairBand","Red color ",50);
		List<Product> products= new ArrayList<>();
		products.add(product1);
		products.add(product2);
		
		when(productRepository.findByProductPriceBetween(0, 100)).thenReturn(products);
		List<Product> prods=productService.findByProductPriceBetween(0, 100);
		assertEquals(prods.size(),products.size());
		assertEquals(product1.getProductName(),prods.get(0).getProductName());
		
	}
	
	@Test
	@Order(7)
	void test_deleteByProductId() {

		Product product=new Product(1,"hairClips","thin and large size clips",45);

		ProductDto productDto=new ProductDto();
		productDto.setProduct(product);
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

		productService.deleteProduct(1);

		verify(productRepository, times(1)).delete(product);
		assertEquals(0, productRepository.count());
	}

	@Test
	@Order(8)
	void test_deleteAll() {

		Product product1=new Product(1,"hairClips","thin and large size clips",45);

		Product product2=new Product(2,"hairBand","Red color ",50);
		List<Product> products= new ArrayList<>();
		products.add(product1);
		products.add(product2);
		when(productRepository.findAll()).thenReturn(products);

		productService.deleteAll();

		verify(productRepository, times(1)).deleteAll();
		assertEquals(0, productRepository.count());
	}

}
