package com.storemanagement.vinyl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storemanagement.vinyl.Model.Order;

public interface OrderRepo extends JpaRepository<Order, String> {
}