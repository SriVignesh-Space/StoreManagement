package com.storemanagement.vinyl.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Service.CustomerService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    CustomerService customerService;

    public AdminController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomer() {
        System.out.println("Reached admin");
        return customerService.getAllCustomers();
    }
}
