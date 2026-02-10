package com.storemanagement.vinyl.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.storemanagement.vinyl.Exception.VinylException;
import com.storemanagement.vinyl.Model.CartItem;
import com.storemanagement.vinyl.Model.Customer;
import com.storemanagement.vinyl.Model.Vinyl;
import com.storemanagement.vinyl.Repository.CartItemRepo;
import com.storemanagement.vinyl.Repository.VinylRepo;

@Service
public class VinylService {
    VinylRepo vinylRepo;
    CustomerService customerService;
    CartItemRepo cartItemRepo;

    VinylService(VinylRepo vinylRepo, CustomerService customerService, CartItemRepo cartItemRepo){
        this.vinylRepo = vinylRepo;
        this.customerService =  customerService;
        this.cartItemRepo = cartItemRepo;
    }

    public Vinyl addVinyl(Vinyl vinyl){
        return vinylRepo.save(vinyl);
    }

    public List<Vinyl> getAllVinyl(){
        return vinylRepo.findAll();
    }

    public Vinyl getVinylById(String id){
        return vinylRepo.findById(id).orElseThrow(() ->  new VinylException("Vinyl not found with Id : " + id ));
    }

    public Vinyl updateVinyl(String vinylId,Vinyl vinyl){
        Vinyl data = vinylRepo.findById(vinylId).orElseThrow(() -> new VinylException("Vinyl not found with Id : " + vinylId + " Updation failed"));
        if(vinyl != null){
            vinylRepo.delete(data);
            return vinylRepo.save(vinyl);
        }
        else{
            throw new VinylException("vinyl is not in correct format " + vinylId  + "Updation failed");
        }
    }

    public Map<String, String> deleteVinyl(String id){
        Map<String, String> response = new HashMap<>();
        Vinyl vinyl = vinylRepo.findById(id).orElseThrow(() -> new VinylException("Vinyl not found with Id : " + id + " Deletion failed"));

        vinylRepo.delete(vinyl);
        response.put("Message", "Vinyl id Deleted "+id);
        
        return response;
    }

    // order logic
    public Vinyl addVinylToCustomer(String customerId, String vinylId){

        Customer customer = customerService.getCustomerByID(customerId);
        Vinyl vinyl = vinylRepo.findById(vinylId).orElseThrow(() ->  new VinylException("Vinyl not found with Id : " + vinylId + " Deletion failed"));

        if(vinyl.getStockQuantity() <= 0) throw new VinylException("Out of Stock : " + vinylId);
        vinyl.setStockQuantity(vinyl.getStockQuantity() - 1);

        customer.getBoughtVinyl().add(vinyl);
        vinyl.getCustomers().add(customer);
        vinylRepo.save(vinyl);
        customerService.saveCustomer(customer);
        return vinyl;
    }


    // add cart feature
    public CartItem addCartItem(String customerId, String vinylId){

        Customer customer = customerService.getCustomerByID(customerId);

        Vinyl vinyl = vinylRepo.findById(vinylId).orElseThrow(() -> new VinylException("Vinyl Not found : "+vinylId + "Cart Updation Failed"));

        CartItem items = cartItemRepo.findByCustomerAndVinyl(customer, vinyl);
        
        if(items != null){
            throw new VinylException(vinylId + "already in your cart ");
        }

        CartItem cartItem = new CartItem();
        cartItem.setCustomer(customer);
        cartItem.setVinyl(vinyl);
        cartItemRepo.save(cartItem);
        return cartItem;
    }
}