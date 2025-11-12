package com.robertevans.backend.services;

import com.robertevans.backend.entities.Cart;
import com.robertevans.backend.entities.CartItem;
import com.robertevans.backend.entities.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Purchase {
    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems = new HashSet<>();
}
