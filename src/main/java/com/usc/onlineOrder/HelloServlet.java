package com.usc.onlineOrder;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usc.onlineOrder.entity.Customer;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        JSONObject customer = new JSONObject();
        customer.put("email", "yuedilia@usc.edu");
        customer.put("first_name", "Eddy");
        customer.put("last_name", "Liang");
        customer.put("age", 23);
        response.getWriter().print(customer);

//        String customer = request.getParameter("customer");
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Hello " + customer + "</h1>");
//        out.println("</body></html>");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
//        String email = jsonRequest.getString("email");
//        String firstName = jsonRequest.getString("first_name");
//        String lastName = jsonRequest.getString("last_name");
//        int age = jsonRequest.getInt("age");
//        System.out.println(email);
//        System.out.println(firstName+" "+lastName);
//        System.out.println(age);

        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.readValue(IOUtils.toString(request.getReader()), Customer.class);
        System.out.println(customer.getEmail());
        System.out.println(customer.getFirstName() + " "+customer.getLastName());

        // return "ok" status to client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "fuck you");
        response.getWriter().print(jsonResponse);
    }

    public void destroy() {
    }
}