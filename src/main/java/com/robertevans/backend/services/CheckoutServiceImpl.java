package com.robertevans.backend.services;

import com.robertevans.backend.dao.CustomerRepository;
import com.robertevans.backend.entities.Cart;
import com.robertevans.backend.entities.CartItem;
import com.robertevans.backend.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();

        if (cart==null || cart.getCartItem() == null || cart.getCartItem().isEmpty()){
            return new PurchaseResponse("Validation constaraint Error: Cart is empty.");
        }

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItem();
        cartItems.forEach(item -> cart.add(item));

        Customer customer = purchase.getCustomer();
        customer.add(cart);

        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
