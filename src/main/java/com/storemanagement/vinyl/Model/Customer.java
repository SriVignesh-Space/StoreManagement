package com.storemanagement.vinyl.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;

    private String name;
    private String email, phone;

    @ManyToMany(mappedBy = "customers")
    @JsonIgnore
    List<Vinyl> boughtVinyl = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItem> cartItems = new ArrayList<>();
}