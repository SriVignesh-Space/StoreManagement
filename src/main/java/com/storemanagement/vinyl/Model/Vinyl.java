package com.storemanagement.vinyl.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Vinyl {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String vinylId;

    private String title;
    private String imageUrl;
    private String artist, language, genre;
    private double price;
    private int stockQuantity;


    @OneToMany(mappedBy = "vinyl")
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public String toString() {
        return "Vinyl [vinylId=" + vinylId + ", title=" + title + ", imageUrl=" + imageUrl + ", artist=" + artist
                + ", language=" + language + ", genre=" + genre + ", price=" + price + ", stockQuantity="
                + stockQuantity + ", orderItem=" + orderItems + "]";
    }


}