package com.robertevans.backend.services;

import com.robertevans.backend.dao.CartRepository;
import com.robertevans.backend.dao.CustomerRepository;
import com.robertevans.backend.entities.Cart;
import com.robertevans.backend.entities.CartItem;
import com.robertevans.backend.entities.Customer;
import com.robertevans.backend.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();
        cart.setId(null);
        cart.setStatus(StatusType.ordered);


        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));
        cart.setCustomer(purchase.getCustomer());

        if (cart==null || cart.getCartItems() == null || cart.getCartItems().isEmpty()){
            return new PurchaseResponse("Validation Constraint Error: Cart is empty.");
        }
        else {
            cartRepository.save(cart);
            return new PurchaseResponse(orderTrackingNumber);
        }


    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
