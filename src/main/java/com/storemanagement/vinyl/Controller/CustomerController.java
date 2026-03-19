package com.storemanagement.vinyl.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storemanagement.vinyl.Model.Address;
import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Model.Order;
import com.storemanagement.vinyl.Service.CustomerService;
import com.storemanagement.vinyl.dto.CustomerDto;
import com.storemanagement.vinyl.dto.OrderDto;

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

    @GetMapping("/me")
    public ResponseEntity<CustomerDto> AuthMe(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(authentication.getPrincipal());

        Customer user = (Customer) authentication.getPrincipal();

        // return ResponseEntity.ok(Map.of(
        //     "email", user.getEmail(),
        //     "name", user.getName(),
        //     "role", user.getRole(),
        //     "userId", user.getCustomerId(),
        //     "cart" , user.getCartItems(),
        //     "orderedItems", user.getBoughtVinyl()
        // ));
        
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUserId(user.getCustomerId());
        customerDto.setRole(user.getRole().name());
        customerDto.setEmail(user.getEmail());
        customerDto.setPhone(user.getPhone());
        customerDto.setUsername(user.getName());
        customerDto.setCartItem(user.getCartItems());
        customerDto.setAddresses(user.getAddresses());
        customerDto.setOrders(user.getOrders());

        return ResponseEntity.ok(customerDto);
    }
    

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerByID(id);
    }
    
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable String customerId, @RequestBody CustomerDto customer) {
        System.out.println(customerId);
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/{customerId}")
    public Map<String, String> deleteCustomer(@PathVariable String customerId){
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping("/vinyl")
    public List<Order> getVinylForCustomer(@RequestParam String customerId) {
        return customerService.getVinylForCustomer(customerId);
    }
    
    @PostMapping("/{customerId}/address")
    public Customer addAddress(@PathVariable String customerId,@RequestBody Address address) {
        return customerService.addAddress(customerId, address);
    }
    
    @DeleteMapping("/removeaddress")
    public Customer removeAddress(@RequestParam String customerId, @RequestParam String addressId){
        return customerService.deleteAddress(customerId, addressId);
    }

    @PostMapping("/order")
    public Order addVinyl(@RequestBody OrderDto orderDto) {
        return customerService.addVinylToCustomer(orderDto);
    } 
}