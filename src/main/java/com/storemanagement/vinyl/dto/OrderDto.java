package com.storemanagement.vinyl.dto;

import java.util.Map;

public class OrderDto {
    private String customerId;
    private Map<String, Integer> orderVinyls;
    private String addressId;
    private String phone;
    private String name="";   

    
    

    public OrderDto() {
    }   


    

    @Override
    public String toString() {
        return "OrderDto [customerId=" + customerId + ", orderVinyls=" + orderVinyls + ", addressId=" + addressId
                + ", phone=" + phone + ", name=" + name + "]";
    }




    public OrderDto(String customerId, Map<String, Integer> orderVinyls, String addressId, String phone) {
        this.customerId = customerId;
        this.orderVinyls = orderVinyls;
        this.addressId = addressId;
        this.phone = phone;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public Map<String, Integer> getOrderVinyls() {
        return orderVinyls;
    }
    public void setOrderVinyls(Map<String, Integer> orderVinyls) {
        this.orderVinyls = orderVinyls;
    }
   
    public String getAddressId() {
        return addressId;
    }
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
}
