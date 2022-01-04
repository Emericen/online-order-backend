package com.usc.onlineOrder.service;

import com.usc.onlineOrder.dao.CartDao;
import com.usc.onlineOrder.entity.Cart;
import com.usc.onlineOrder.entity.Customer;
import com.usc.onlineOrder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartDao cartDao;

    public Cart getCart() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        Customer customer = customerService.getCustomer(email);
        Cart cart = customer.getCart();
        double totalPrice = 0;
        for (OrderItem item : cart.getOrderItemList()) {
            totalPrice += item.getPrice();
        }
        cart.setTotalPrice(totalPrice);
        return customer.getCart();
    }

    public void clearCart() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        Customer customer = customerService.getCustomer(email);
        Cart cart = customer.getCart();
        cartDao.removeAllCartItems(cart);
    }
}
