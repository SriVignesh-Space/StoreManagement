package com.storemanagement.vinyl.Utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Repository.CustomerRepo;

@Component
public class AdminInitializer {

    @Bean
    public CommandLineRunner createAdmin(CustomerRepo customerRepo, PasswordEncoder passwordEncoder){
        return args -> {
           if(customerRepo.findByEmail("admin@admin.com").isEmpty()){
            Customer customer = new Customer();
            customer.setName("admin");
            customer.setEmail("admin@admin.com");
            customer.setPassword(passwordEncoder.encode("1234"));
            customer.setPhone("12312342342");
            customer.setRole("ROLE_ADMIN");
            customerRepo.save(customer);
            System.out.println("Admin user Created");
            System.out.println("Username : " + customer.getEmail());
            System.out.println("Password : " + customer.getPassword());
           }
        };
    }
}
