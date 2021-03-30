package com.coupons_system.login;

import com.coupons_system.service.AdminServicesimpl;
import com.coupons_system.service.ClientServices;
import com.coupons_system.service.CompanyServicesimpl;
import com.coupons_system.service.CustomerServicesimpl;


public class LoginManager {
 
    // instance of Login Manager
    private static LoginManager instance = null; 
  
  
    private LoginManager() 
    { 
    } 
  
    // create instance of Login Manager
    public static LoginManager getInstance() 
    { 
        if (instance == null) 
            instance = new LoginManager(); 
  
        return instance; 
    }
    public ClientServices login(String email, String password,ClientType clientType) {
    	if(clientType.equals(ClientType.Adminstator)) {
    		ClientServices admin=new AdminServicesimpl();
    		if(admin.login(email, password)==true)
    			return admin;


    	}else if(clientType.equals(ClientType.Company)) {
    		ClientServices company=new CompanyServicesimpl();
    		if(company.login(email, password)==true)
    			return company;

    	}else if(clientType.equals(ClientType.Customer)) {
    		ClientServices customer=new CustomerServicesimpl();
    		if(customer.login(email, password)==true)
    			return customer;
    	}
    	return null;

    }
  
 
} 

