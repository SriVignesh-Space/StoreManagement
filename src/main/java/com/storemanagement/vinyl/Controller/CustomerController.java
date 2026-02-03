package com.storemanagement.vinyl.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storemanagement.vinyl.Model.Address;
import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Model.Vinyl;
import com.storemanagement.vinyl.Service.CustomerService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerByID(id);
    }
    
    @PostMapping("/")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        System.out.println(customerId);
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/{customerId}")
    public Map<String, String> deleteCustomer(@PathVariable String customerId){
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping("/vinyl")
    public List<Vinyl> getVinylForCustomer(@RequestParam String customerId) {
        return customerService.getVinylForCustomer(customerId);
    }
    
    @PostMapping("/{customerId}/address")
    public Customer addAddress(@PathVariable String customerId,@RequestBody Address address) {
        return customerService.addAddress(customerId, address);
    }
    
}