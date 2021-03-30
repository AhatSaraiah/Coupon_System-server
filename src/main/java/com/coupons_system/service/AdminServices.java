package com.coupons_system.service;


import com.coupons_system.model.Company;
import com.coupons_system.model.Customer;

public interface AdminServices {
	public void addCompany(Company company);
	public Company getOneCompany(int id);
	public Iterable<Company> getAllCompanies();
	public void updateCustomer(Customer customer);
	public void deleteCustomer(int customerID);
	public Iterable<Customer> getAllCustomers();
	public Customer getOneCustomer(int customerID);
	public Boolean isCompanyExists(int companyId);
	public void updateCompany(Company company);
	public void deleteCompany(int companyID);
	public void addCustomer(Customer customer);
	public boolean login(String email, String password);

}