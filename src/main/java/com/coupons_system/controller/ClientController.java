package com.coupons_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.coupons_system.service.AdminServices;


@RestController
public abstract class ClientController {

	@Autowired
	protected AdminServices adminServices;
	   
	public abstract boolean login(String email,String password);


}
