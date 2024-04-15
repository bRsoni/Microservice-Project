package com.webserviceex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webserviceex.model.OrderRequest;
import com.webserviceex.repository.OrderRepository;

@Service
public interface OrderService {
	
	
	public Long placeOrder(OrderRequest orderRequest);
	

}
