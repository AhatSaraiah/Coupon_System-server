package com.coupons_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coupons_system.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public boolean existsByEmailAndPassword(String email, String password);
	public boolean existsByEmailOrFirstName(String email, String firstName);
	public boolean existsByEmail(String email);


}
