package com.webserviceex.service;

import java.time.Instant;

import com.webserviceex.client.PaymentService;
import com.webserviceex.client.request.PaymentRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webserviceex.client.ProductService;
import com.webserviceex.entity.Order;
import com.webserviceex.model.OrderRequest;
import com.webserviceex.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductService productService;

	@Autowired
	private PaymentService paymentService;
	@Override
	public Long placeOrder(OrderRequest orderRequest) {
		log.info("Before Placing Order");
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("After checking product availablity for the product"+orderRequest.getProductId());
		Order order = Order.builder()
					  .productId(orderRequest.getProductId())
					  .price(orderRequest.getPrice())
					  .orderDate(Instant.now())
					  .orderStatus("CREATED")
					  .quantity(orderRequest.getQuantity())
					  .build();
		log.info("Order Placed ");
		order = orderRepository.save(order);
		log.info("calling payment service with order id"+order.getOrderId());
		PaymentRequest paymentRequest = PaymentRequest.builder()
						.amount(orderRequest.getPrice())
				        .paymentMode(orderRequest.getPaymentMode())
				        .orderId(order.getOrderId())
				        .build();
		log.info("payment Request " + paymentRequest);
		String status = StringUtils.EMPTY;
		try{
			paymentService.doPayment(paymentRequest);
			status="SUCCESS";
		}catch (Exception e){
			log.info("paymentRequest"+paymentRequest);
			status="FAILED";
		}
		order.setOrderStatus(status);
		order = orderRepository.save(order);
		log.info("Product saved Successfully after placing"+order.getOrderId());
		return order.getOrderId();

	}

}
