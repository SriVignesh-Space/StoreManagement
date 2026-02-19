package com.storemanagement.vinyl.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.storemanagement.vinyl.Repository.CustomerRepo;

@Service
public class CustomerDetailsService implements UserDetailsService{

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email Not found. Register and continue"));
    }
    
}
