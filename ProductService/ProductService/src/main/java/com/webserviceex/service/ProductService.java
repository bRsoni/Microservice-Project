package com.webserviceex.service;

import org.springframework.stereotype.Service;

import com.webserviceex.exception.ProductServiceException;
import com.webserviceex.model.ProductRequst;
import com.webserviceex.model.ProductResponse;
@Service
public interface ProductService {

	Long addProduct(ProductRequst productRequest);

	ProductResponse getProductById(Long productId) throws ProductServiceException;

	 void reduceQuantity(Long productId, int quantity) throws ProductServiceException;
		

}
