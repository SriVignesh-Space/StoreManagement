package com.storemanagement.vinyl.Repository;

import org.springframework.data.repository.CrudRepository;

import com.storemanagement.vinyl.Model.Address;

public interface AddressRepo extends CrudRepository<Address, String> {
}
