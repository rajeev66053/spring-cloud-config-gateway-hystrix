package com.java.orderservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.orderservice.api.common.Payment;
import com.java.orderservice.api.common.TransactionRequest;
import com.java.orderservice.api.common.TransactionResponse;
import com.java.orderservice.api.entity.Order;
import com.java.orderservice.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {

        return orderService.saveOrder(request);

        //do a rest call to payment and pass the order id
    }
}