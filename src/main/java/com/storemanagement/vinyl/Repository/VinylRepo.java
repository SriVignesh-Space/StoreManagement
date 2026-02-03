package com.storemanagement.vinyl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storemanagement.vinyl.Model.Vinyl;

public interface VinylRepo extends JpaRepository<Vinyl, String> {
}