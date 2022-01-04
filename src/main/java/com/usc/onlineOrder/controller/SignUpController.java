package com.usc.onlineOrder.controller;

import com.usc.onlineOrder.entity.Customer;
import com.usc.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class SignUpController {
    //
    // I. Spring uses the MVC structure
    //    #1. Model -- database and storage tier -- stored in database s.a. AWS, google cloud ..
    //    #2. Controller -- logic tier. Send query to database and get result and do whatever
    //        needed with the data -- stored in Application / Web server, our computer in this case
    //    #3. View -- Presentation tier -- UIs, all the React, HTML and CSS, etc.
    //
    // II. Spring MVC controller offers the REST api that the presentation tier can use.
    //
    // III. !! Request Mapping is important for linking url and request method and function.
    //

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody Customer customer) {
        // DO SOMETHING
//        System.out.println(customer.getEmail());

        customerService.signUp(customer);


        // NOTE: you can also use response like HelloServlet Classes:
        // pass in response parameter and do "response.setStatus(500);"

    }


}
