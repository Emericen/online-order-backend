package com.usc.onlineOrder.controller;

import com.usc.onlineOrder.entity.MenuItem;
import com.usc.onlineOrder.entity.Restaurant;
import com.usc.onlineOrder.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller  // meaning this class is used to define the REST api
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurant() {
        return menuInfoService.getRestaurant();
    }

    @RequestMapping(value = "/restaurants/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable("restaurantId") int restaurantId) {
        return menuInfoService.getAlMenuItem(restaurantId);
    }
}
