package com.msd.product.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.msd.product.dto.ProductDto;
import com.msd.product.exception.DuplicateNotAllowedException;
import com.msd.product.exception.EmptyFieldException;
import com.msd.product.exception.ResourceNotFoundException;
import com.msd.product.model.Product;

import com.msd.product.repository.ProductRepository;


/**
 * Product Service
 * 
 * @author ME20385435
 * @version 3.0.5
 * @since 2023-04-28
 */


@Service
public class ProductService  implements ProductServiceInterface{

	@Autowired
	private ProductRepository productrepository;
	
	@Autowired
	private SequenceGeneratorService serviceSequence;
	
	/**
	 * Method Name:addProduct, This method is used for add new Product
	 * 
	 * @param productDto This is parameter to this method
	 * @exception DuplicateNotAllowedException if Product Already Exists
	 * @exception EmptyFieldException if Field is Empty
	 * @return product
	 * @see ProductService
	 */

	@Override
	public Product addProduct(ProductDto productDto) {
		
		Product product=productDto.getProduct();
		Product checkProduct = productrepository.findByProductName(product.getProductName());
		if(checkProduct!=null)
			throw new DuplicateNotAllowedException("Product Already Exists");
		if(product.getProductDescription()==null||product.getProductName()==null || product.getProductPrice()<=0) {
			throw new EmptyFieldException("Fields Must not be empty");
		}
		product.setId(serviceSequence.generateSequence(Product.SEQUENCE_NAME));
		return productrepository.save(product);
	}

	/**
	 * Method Name:updateProduct, This method is used for update the Product
	 * 
	 * @param  productId This is parameter1 to this method
	 * @param  product This is parameter2 to this method
	 * @exception ResourceNotFoundException if Product Not Exists
	 * @return product
	 * @see ProductService
	 */
	
	@Override
	public Product updateProduct(long productId,Product product) {
		
		Product updateProduct= productrepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("No product Found with that Id:" + productId));
		updateProduct.setId(product.getId());
		updateProduct.setProductName(product.getProductName());
		updateProduct.setProductDescription(product.getProductDescription());
		updateProduct.setProductPrice(product.getProductPrice());
		productrepository.save(updateProduct);
		return updateProduct;
	}

	/**
	 * Method Name: deleteProduct, This method is used for delete the Product
	 * by using productId
	 * 
	 * @param productId This is parameter to this method
	 * @exception ResourceNotFoundException if Product Not Exists
	 * @see ProductService
	 */
	
	
	
	@Override
	public void deleteProduct(long productId) {
	Product product=getProduct(productId);
		productrepository.delete(product);
	}

	
	/**
	 * Method Name:getProduct  ,This method is used for find product by
	 * using productId
	 * 
	 * @param productId This is parameter to this method
	 * @exception ResourceNotFoundException if Product Not Exists
	 * @return product 
	 * @see ProductService
	 */
	
	
	
	@Override
	public Product getProduct(long productId) {
		
		return productrepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("No product Exists with that Id:" + productId));
	}

	
	/**
	 * Method Name:findByProductPriceBetween  , This method is used for find products
	 * by using price minimum to maximum
	 * 
	 * @param priceMin This is parameter1 to this method
	 * @param priceMax This is parameter2 to this method
	 * @exception ResourceNotFoundException if Products  are Not Available
	 * @return products
	 * @see ProductService 
	 */
	
	@Override
	public List<Product> findByProductPriceBetween(double priceMin, double priceMax) {
		
		List<Product> products= productrepository.findByProductPriceBetween(priceMin, priceMax);
		if (products.isEmpty()) {
			throw new ResourceNotFoundException("No products Available with that price Range:" +priceMin+"-"+priceMax);
		}
		return products;
	}

	
	/**
	 * Method Name:deleteAll, This method is used for delete all products
	 * @exception ResourceNotFoundException if Products are Not Exists
	 * @see ProductService
	 */
	@Override
	public void deleteAll() {
		List<Product> products = productrepository.findAll();
		if (products.isEmpty()) {
			throw new ResourceNotFoundException("No products Available");
		}
		productrepository.deleteAll();
	}

	/**
	 * Method Name:getProductByName  , This method is used for find product
	 * by using productName
	 * 
	 * @param productName This is parameter to this method
	 * @exception ResourceNotFoundException if Product Not Exists
	 * @return product 
	 * @see ProductService
	 */

	
	
	@Override
	public Product getProductByName(String productName) {
		Product product = productrepository.findByProductName(productName);
		if (product == null) {
			throw new ResourceNotFoundException("No product Available with that Name:"+productName);
		}
		return product;
	}

	
	/**
	 * Method Name:getAll, This method is used for find all products
	 * 
	 * @exception ResourceNotFoundException if Product Not Exists
	 * @return products 
	 * @see ProductService
	 */
	@Override
	public List<Product> getAll() {
		List<Product> products = productrepository.findAll();
		if (products.isEmpty()) {
			throw new ResourceNotFoundException("No products Available");
		}
		return products;
	}
	
	
}
