package com.java.orderservice.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.orderservice.api.common.Payment;
import com.java.orderservice.api.common.TransactionRequest;
import com.java.orderservice.api.common.TransactionResponse;
import com.java.orderservice.api.entity.Order;
import com.java.orderservice.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    //From spring -cloud-config yml file
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    private Logger log= LoggerFactory.getLogger(OrderService.class);

    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {

        String response ="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        log.info("Order Service request : {}",new ObjectMapper().writeValueAsString(request));

//        Payment paymentResponse=restTemplate.postForObject("http://localhost:9191/payment/doPayment",payment,Payment.class);

//        Payment paymentResponse=restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment,Payment.class);

        Payment paymentResponse=restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);


        log.info("Payment Service response from OrderService Rest call : {}",new ObjectMapper().writeValueAsString(paymentResponse));

        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "There is a failure in payment api, order added to cart.";

        orderRepository.save(order);

        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}