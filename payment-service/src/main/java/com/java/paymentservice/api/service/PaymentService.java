package com.java.paymentservice.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.paymentservice.api.entity.Payment;
import com.java.paymentservice.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;


    private Logger log= LoggerFactory.getLogger(PaymentService.class);


    public Payment doPaymewnt(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());

        log.info("Payment Service request : {}",new ObjectMapper().writeValueAsString(payment));
        return paymentRepository.save(payment);
    }


    public String paymentProcessing(){
        //api should be third party gateway
       return new Random().nextBoolean()?"success":"false";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        Payment payment= paymentRepository.findByOrderId(orderId);
        log.info("Payment Service findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}