package com.usc.onlineOrder.service;

import com.usc.onlineOrder.dao.CustomerDao;
import com.usc.onlineOrder.entity.Customer;
import com.usc.onlineOrder.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Although our project service logic is rather simple
// it is very common for real world application to get
// complicated and have multiple steps in its business
// logic, thus it is done in this extra layer

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnabled(true);
        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }

}

