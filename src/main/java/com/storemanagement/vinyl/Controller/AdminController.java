package com.storemanagement.vinyl.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Model.Order;
import com.storemanagement.vinyl.Model.OrderStatus;
import com.storemanagement.vinyl.Service.CustomerService;
import com.storemanagement.vinyl.dto.OrderDto;
import com.storemanagement.vinyl.dto.StatusDto;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



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

    @GetMapping("/vieworders")
    public List<Order> getOrders() {
        return customerService.getAllOrders();
    }
    
    @PutMapping("order/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody StatusDto status) { 
        OrderStatus updated = OrderStatus.valueOf(status.status);  
        Order order =  customerService.updateOrderStatus(id, updated);
        return order;
    }
}
