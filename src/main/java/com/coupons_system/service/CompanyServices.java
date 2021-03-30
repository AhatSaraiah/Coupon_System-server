package com.coupons_system.service;

import java.util.Set;

import com.coupons_system.model.Category;
import com.coupons_system.model.Company;
import com.coupons_system.model.Coupon;

public interface CompanyServices {

	public Company getCompanyDetails();
	public Set<Coupon> getCompanyCoupons(double maxPrice);
	public Set<Coupon> getCompanyCoupons(Category category);
	public Set<Coupon> getCompanyCoupons();
	public void addCoupon(Coupon coupon);
	public void deleteCoupon(int couponID);
	public void updateCoupon(Coupon coupon);
	//public  Set<Coupon> getCompanyCoupons(int id);
	public boolean login(String email, String password);




}
