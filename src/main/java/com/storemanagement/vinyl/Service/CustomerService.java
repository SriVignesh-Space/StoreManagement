package com.storemanagement.vinyl.Service;

import com.storemanagement.vinyl.Repository.OrderRepo;
import com.storemanagement.vinyl.Repository.VinylRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.storemanagement.vinyl.Exception.CustomerException;
import com.storemanagement.vinyl.Exception.VinylException;
import com.storemanagement.vinyl.Model.Address;
import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Model.Order;
import com.storemanagement.vinyl.Model.OrderItem;
import com.storemanagement.vinyl.Model.OrderStatus;
import com.storemanagement.vinyl.Model.Role;
import com.storemanagement.vinyl.Model.Vinyl;
import com.storemanagement.vinyl.Repository.AddressRepo;
import com.storemanagement.vinyl.Repository.CustomerRepo;
import com.storemanagement.vinyl.dto.CustomerDto;
import com.storemanagement.vinyl.dto.OrderDto;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    OrderRepo orderRepo;
    CustomerRepo customerRepo;
    AddressRepo addressRepo;
    PasswordEncoder passwordEncoder;
    VinylRepo vinylRepo;
    
    public CustomerService(CustomerRepo customerRepo, AddressRepo addressRepo, PasswordEncoder passwordEncoder, OrderRepo orderRepo, VinylRepo vinylRepo){
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.passwordEncoder = passwordEncoder;
        this.orderRepo = orderRepo;
        this.vinylRepo = vinylRepo;
    }
    
    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer getCustomerByID(String customerId){
        return customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer Not Found :" + customerId));
    }

    public Customer addCustomer(Customer customer){

        Customer data = customerRepo.findByEmail(customer.getEmail()).orElse(null);
        if(data != null){
            throw new CustomerException("Customer Already found");
        }
        customer.setRole(Role.USER);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        List<Address> address = customer.getAddresses();
        if(address != null){
            for(Address ad : address){
                ad.setCustomer(customer);
            }
        }

        return customerRepo.save(customer);
    }

    public Customer updateCustomer(String customerId, CustomerDto customer){
        Customer data = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer Not Found :" + customerId + "Updation failed"));
        if(customer != null){
            data.setName(customer.getUsername());
            data.setPhone(customer.getPhone());
            return customerRepo.save(data);
        }
        else throw new CustomerException("Request Body is not in correct format" + customerId + "Updation failed");
    }

    public Map<String, String> deleteCustomer(String customerId){
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer Not Found :" + customerId + "Deletion failed"));

        Map<String, String> response = new HashMap<>();
        customerRepo.delete(customer);

        response.put("Message", "Deleted Successfully Deleted" + customerId);
        return response;
    }

    public Customer saveCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public List<Order> getVinylForCustomer(String customerId){
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer Not Found :" + customerId + "get Vinyl failed"));
        return customer.getOrders();
    }

    // address logic 
    public Customer addAddress(String customerId, Address address){
        System.out.println(address.toString());
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer Not found : "+ customerId+" address updation failed" ));
        address.setCustomer(customer);
        addressRepo.save(address);
        customer.getAddresses().add(address);
        return customerRepo.save(customer);        
    }

    public Customer deleteAddress(String customerId, String addressId ){
        Customer customer = getCustomerByID(customerId);
        Address address = addressRepo.findById(addressId).orElseThrow(() -> new CustomerException("Address not Found : " + addressId + " Address deletion failed")); 
        customer.getAddresses().remove(address);
        addressRepo.deleteById(addressId);
        System.out.println("Address Deleted");
        return customerRepo.save(customer);
    }
 
    @Transactional
    public Order addVinylToCustomer(OrderDto orderDto){
        
        String customerId = orderDto.getCustomerId();
        Map<String, Integer> orderVinyls = orderDto.getOrderVinyls();
        String addressId = orderDto.getAddressId();

        Customer customer = getCustomerByID(customerId);

        Address address = addressRepo.findById(addressId).orElseThrow(()-> new CustomerException("Address Not Found "+ addressId));;

        Order order = new Order();
        order.setCustomer(customer);
        order.setAddress(address.getAddress());
        order.setCountry(address.getCountry());
        order.setPincode(address.getPincode());
        order.setPhone(orderDto.getPhone());
        order.setStatus(OrderStatus.ORDERED);

        for(Map.Entry<String,Integer> entry : orderVinyls.entrySet()){

            String vinylId = entry.getKey();
            int quantity = entry.getValue();

            Vinyl vinyl = vinylRepo.findById(vinylId).orElseThrow(() -> new VinylException("Vinyl id not found " + vinylId ));
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            if(vinyl.getStockQuantity() - quantity < 0){
                throw new VinylException("Out of stock " + vinyl.getVinylId());
            } 

            vinyl.setStockQuantity(vinyl.getStockQuantity() - quantity);
            orderItem.setVinyl(vinyl);
            orderItem.setQuantity(quantity);
            vinyl.getOrderItems().add(orderItem);
            order.getOrderItems().add(orderItem);
        }
        orderRepo.save(order);
        customer.getOrders().add(order);
        return order;
    }
}