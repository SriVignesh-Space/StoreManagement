package com.storemanagement.vinyl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storemanagement.vinyl.Model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String>{
}
