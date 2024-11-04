package com.mangahis.ecommerce.dto;

import com.mangahis.ecommerce.entity.Address;
import com.mangahis.ecommerce.entity.Customer;
import com.mangahis.ecommerce.entity.Order;
import com.mangahis.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
