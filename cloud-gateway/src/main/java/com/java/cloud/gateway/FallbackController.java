package com.java.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceAllBack(){
        return Mono.just("Order Service is taking too long to respond or is down. Please try again later.");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String> paymentServiceAllBack(){
        return Mono.just("Payment Service is taking too long to respond or is down. Please try again later.");
    }
}