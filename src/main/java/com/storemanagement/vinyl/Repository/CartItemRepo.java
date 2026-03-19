package com.storemanagement.vinyl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storemanagement.vinyl.Model.CartItem;
import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Model.Vinyl;
import java.util.List;



public interface CartItemRepo extends JpaRepository<CartItem, Long>{
    CartItem findByCustomerAndVinyl(Customer customer, Vinyl vinyl);
    List<CartItem> findByCustomer(Customer customer);
}
