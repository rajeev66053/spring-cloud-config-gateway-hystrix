package com.java.orderservice.api.common;

import com.java.orderservice.api.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private Order order;
    private Payment payment;
}