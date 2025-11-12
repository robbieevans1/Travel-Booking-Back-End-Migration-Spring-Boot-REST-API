package com.robertevans.backend.services;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
