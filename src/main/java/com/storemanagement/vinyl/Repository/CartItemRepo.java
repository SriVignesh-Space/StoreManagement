package com.storemanagement.vinyl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storemanagement.vinyl.Model.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long>{
}
