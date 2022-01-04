package com.usc.onlineOrder.service;

import com.usc.onlineOrder.dao.OrderItemDao;
import com.usc.onlineOrder.entity.Customer;
import com.usc.onlineOrder.entity.MenuItem;
import com.usc.onlineOrder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private OrderItemDao orderItemDao;

    public void saveOrderItem(int menuId) {
        OrderItem orderItem = new OrderItem();
        MenuItem menuItem = menuInfoService.getMenuItem(menuId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        Customer customer = customerService.getCustomer(email);
        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setPrice(menuItem.getPrice());
        orderItem.setQuantity(1);     // we disregard multiple of same order for now
        orderItemDao.save(orderItem);
    }
}
