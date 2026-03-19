package com.storemanagement.vinyl.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customerId")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String  country;

    @Column(nullable = false)
    private String pincode;
    
    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "CreatedAt",nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}