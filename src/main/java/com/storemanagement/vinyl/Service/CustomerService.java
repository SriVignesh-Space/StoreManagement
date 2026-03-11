package com.storemanagement.vinyl.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.storemanagement.vinyl.Exception.CustomerException;
import com.storemanagement.vinyl.Model.Address;
import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Model.Role;
import com.storemanagement.vinyl.Model.Vinyl;
import com.storemanagement.vinyl.Repository.AddressRepo;
import com.storemanagement.vinyl.Repository.CustomerRepo;

@Service
public class CustomerService {

    CustomerRepo customerRepo;
    AddressRepo addressRepo;
    PasswordEncoder passwordEncoder;
    
    public CustomerService(CustomerRepo customerRepo, AddressRepo addressRepo, PasswordEncoder passwordEncoder){
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.passwordEncoder = passwordEncoder;
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

    public Customer updateCustomer(String customerId, Customer customer){
        Customer data = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer Not Found :" + customerId + "Updation failed"));
        if(customer != null){
            customerRepo.delete(data);
            return customerRepo.save(customer);
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

    public List<Vinyl> getVinylForCustomer(String customerId){
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer Not Found :" + customerId + "get Vinyl failed"));
        return customer.getBoughtVinyl();
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
        return customerRepo.save(customer);
    }
    
}