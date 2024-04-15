package com.webserviceex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webserviceex.entity.Product;
import com.webserviceex.exception.ProductServiceException;
import com.webserviceex.model.ProductRequst;
import com.webserviceex.model.ProductResponse;
import com.webserviceex.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Long addProduct(ProductRequst productRequest) {

		Product product = Product.builder().productName(productRequest.getProductName())
				.prices(productRequest.getPrice()).quantity(productRequest.getQuantity()).build();
		productRepository.save(product);

		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(Long productId) throws ProductServiceException {
		Product product = productRepository.findById(productId)
		 .orElseThrow(() -> new ProductServiceException("Product id NOt Found", "Product_Not_Found"));
		ProductResponse productResponse = ProductResponse.builder().productName(product.getProductName())
				.price(product.getPrices()).quantity(product.getQuantity()).build();
		return productResponse;
	}

	@Override
	public void reduceQuantity(Long productId, int quantity) throws ProductServiceException {
		log.info("Reducing Quantity intiated");
		Product product = productRepository.findById(productId)
				 .orElseThrow(() -> new ProductServiceException("Product id NOt Found", "Product_Not_Found"));
		
		if(product instanceof Product) {
			log.info("checking product Quantity");
			if(product.getQuantity() < quantity) {
				throw new ProductServiceException("not having enough quantity of product", "NOT_ENOUGH_QUANTITY");
			}
			product.setQuantity(product.getQuantity() - quantity);
			productRepository.save(product);
			log.info("product saved in db");
		}
	}

}
