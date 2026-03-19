package com.storemanagement.vinyl.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.storemanagement.vinyl.Model.CartItem;
import com.storemanagement.vinyl.Model.Vinyl;
import com.storemanagement.vinyl.Service.VinylService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/vinyl")
public class VinylController {

    VinylService vinylService;

    public VinylController(VinylService vinylService){
        this.vinylService = vinylService;
    }

    @GetMapping("/")
    public List<Vinyl> getAllVinyl() {   
        return vinylService.getAllVinyl();
    }
    
    @GetMapping("/{id}")    
    public Vinyl getVinyl(@PathVariable String id){
        return vinylService.getVinylById(id);
    }

    @PostMapping("/")
    public Vinyl addVinyl(@RequestBody Vinyl vinyl) {
        return vinylService.addVinyl(vinyl);
    }
    
    @PutMapping("/{id}")
    public Vinyl updateVinyl(@PathVariable String id, @RequestBody Vinyl vinyl) {
        return vinylService.updateVinyl(id, vinyl);
    }   

    @DeleteMapping("/{id}")
    public Map<String, String>  deleteVinyl(@PathVariable String id){
        return vinylService.deleteVinyl(id);
    }

    @GetMapping("/addcart")
    public CartItem addCartItem(@RequestParam String customerId, @RequestParam String vinylId) {
        return vinylService.addCartItem(customerId, vinylId);
    } 

    @GetMapping("/getcart")
    public List<Vinyl> getCartForCustomerId(@RequestParam String customerId) {
        return vinylService.getCartForCustomerId(customerId);
    }
    
    @GetMapping("/deletecart")
    public String getMethodName(@RequestParam String customerId, @RequestParam String vinylId){
        if(vinylService.removeCartItem(customerId, vinylId));
            return new String("Delete Item Successfully");
    }
    
}