package com.usc.onlineOrder.service;

import com.usc.onlineOrder.dao.MenuInfoDao;
import com.usc.onlineOrder.entity.MenuItem;
import com.usc.onlineOrder.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MenuInfoService {
    @Autowired
    private MenuInfoDao menuInfoDao;

    public List<Restaurant> getRestaurant() {
        return menuInfoDao.getRestaurants();
    }

    public List<MenuItem> getAlMenuItem(int restaurantId) {
        return menuInfoDao.getAllMenuItem(restaurantId);
    }

    public MenuItem getMenuItem(int id) {
        return menuInfoDao.getMenuItem(id);
    }
}
