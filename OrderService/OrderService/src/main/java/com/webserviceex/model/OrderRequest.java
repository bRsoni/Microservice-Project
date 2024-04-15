package com.webserviceex.model;

import java.time.Instant;

import com.webserviceex.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
	private Long productId;
	private int quantity;
	private Double price;
	private PaymentMode paymentMode;
	

}
