package com.webserviceex.service;

import com.webserviceex.entity.Payment;
import com.webserviceex.model.PaymentRequest;
import com.webserviceex.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Payment Request in PaymentServiceImpl"+paymentRequest);
        Payment payment = Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .build();
        log.info("Payment  in dopayment"+payment);
        paymentRepository.save(payment);
        return payment.getPaymentId();
    }
}
