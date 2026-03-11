package com.storemanagement.vinyl.dto;

import java.util.List;


import com.storemanagement.vinyl.Model.Address;
import com.storemanagement.vinyl.Model.CartItem;
import com.storemanagement.vinyl.Model.Vinyl;

public class CustomerDto{
    private String userId;
    private String username;
    private String role;
    private String email;
    private String phone;
    private List<CartItem> cart;
    private List<Vinyl> orders;
    private List<Address> addresses;

    

    @Override
    public String toString() {
        return "CutomerDto [userId=" + userId + ", username=" + username + ", role=" + role + ", email=" + email
                + ", phone=" + phone + ", cartItem=" + cart + ", orders=" + orders + ", addresses=" + addresses
                + "]";
    }

    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public List<CartItem> getCart() {
        return cart;
    }
    public void setCartItem(List<CartItem> cartItem) {
        this.cart = cartItem;
    }
    public List<Vinyl> getOrders() {
        return orders;
    }
    public void setOrders(List<Vinyl> orders) {
        this.orders = orders;
    }
    public List<Address> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
