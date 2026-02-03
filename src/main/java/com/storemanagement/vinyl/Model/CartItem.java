package com.storemanagement.vinyl.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    
    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName = "customerId" ,nullable = false)
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="vinylId", referencedColumnName = "vinylId" ,nullable = false)
    @JsonIgnore
    private Vinyl vinyl;
}