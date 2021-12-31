package com.java.paymentservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.paymentservice.api.entity.Payment;
import com.java.paymentservice.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;


    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return paymentService.doPaymewnt(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }

}