package com.coupons_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coupons_system.repositories.CompanyRepository;
import com.coupons_system.repositories.CouponRepository;
import com.coupons_system.repositories.CustomerRepository;
@Service
public abstract class ClientServicesimpl implements ClientServices {
	@Autowired
	protected CompanyRepository comRepo;
    @Autowired
	protected CustomerRepository custRepo;
    @Autowired
    protected CouponRepository coupRepo;

public ClientServicesimpl(CompanyRepository comRepo, CustomerRepository custRepo, CouponRepository coupRepo) {
		this.comRepo = comRepo;
		this.custRepo = custRepo;
		this.coupRepo = coupRepo;
	}

public ClientServicesimpl() {
}

@Override
public abstract boolean login(String email, String password);
}
