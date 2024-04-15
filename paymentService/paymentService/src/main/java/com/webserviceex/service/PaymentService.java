package com.webserviceex.service;

import com.webserviceex.model.PaymentRequest;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);
}
