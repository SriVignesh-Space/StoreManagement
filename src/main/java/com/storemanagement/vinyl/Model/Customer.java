package com.storemanagement.vinyl.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Customer implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String role;

    private String phone;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    @JsonIgnore
    List<Vinyl> boughtVinyl = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<CartItem> cartItems = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", password=" + password
                + ", role=" + role + ", phone=" + phone + ", boughtVinyl=" + boughtVinyl + ", addresses=" + addresses
                + ", cartItems=" + cartItems + "]";
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}