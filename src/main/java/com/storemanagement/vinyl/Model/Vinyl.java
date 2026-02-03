package com.storemanagement.vinyl.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vinyl {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String vinylId;

    private String title;
    private String artist, language, genre;
    private double price;
    private int stockQuantity;

    @ManyToMany
    @JoinTable(
        name = "orderedCustomers",
        joinColumns = @JoinColumn(name = "vinylId"),
        inverseJoinColumns = @JoinColumn(name = "customerId")
    )
    List<Customer> customers = new ArrayList<>();

}