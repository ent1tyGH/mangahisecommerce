package com.mangahis.ecommerce.controller;

import com.mangahis.ecommerce.dto.PaymentInfo;
import com.mangahis.ecommerce.dto.Purchase;
import com.mangahis.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.mangahis.ecommerce.service.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        return checkoutService.placeOrder(purchase);
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {

        var paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
        var paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
}

