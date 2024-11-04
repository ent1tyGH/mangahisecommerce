package com.mangahis.ecommerce.service;

import com.mangahis.ecommerce.dto.PaymentInfo;
import com.mangahis.ecommerce.dto.Purchase;
import com.mangahis.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
