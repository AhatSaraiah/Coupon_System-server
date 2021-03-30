package com.coupons_system.utils;

import java.sql.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;

import com.coupons_system.job.CouponExpirationDailyJob;
import com.coupons_system.login.ClientType;
import com.coupons_system.login.LoginManager;
import com.coupons_system.model.Category;
import com.coupons_system.model.Company;
import com.coupons_system.model.Coupon;
import com.coupons_system.model.Customer;
import com.coupons_system.repositories.CompanyRepository;
import com.coupons_system.repositories.CouponRepository;
import com.coupons_system.repositories.CustomerRepository;
import com.coupons_system.service.AdminServices;
import com.coupons_system.service.AdminServicesimpl;
import com.coupons_system.service.CompanyServices;
import com.coupons_system.service.CompanyServicesimpl;
import com.coupons_system.service.CustomerServices;
import com.coupons_system.service.CustomerServicesimpl;
//@AutoConfigureTestDatabase(replace=Replace.NONE)

//@DataJpaTest
public final class Test  {

    private static Test INSTANCE;
    @Autowired
	CouponRepository coupRepo;
    @Autowired
	CompanyRepository compRepo;
    @Autowired
	CustomerRepository custRepo;

    private Test() {        
    }
    
    public static Test getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Test();
        }
        
        return INSTANCE;
    }
    
    
    //@org.junit.jupiter.api.Test
	public  void TestAll(){
//
		final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		CustomerServices customerServices=new CustomerServicesimpl(0, compRepo, custRepo, coupRepo);
		CompanyServices companyServices=new CompanyServicesimpl(0, compRepo, custRepo, coupRepo);
		AdminServices adminServices =new AdminServicesimpl(compRepo, custRepo, coupRepo);	
		CouponExpirationDailyJob couponExpirationDailyJob = CouponExpirationDailyJob.getInstance();
		couponExpirationDailyJob.deleteExpiredCoupons(executor);
		
		//get instance of Login manager 
		LoginManager loginManager = LoginManager.getInstance();


			//login as an admin 
			loginAsAdmin(loginManager, adminServices);

			//company login
			//loginAsCompany(loginManager, companyFacade),loginManager,companiesDAO, customersDAO, couponsDAO;

			//customer login
			//loginAsCustomer(loginManager,customerServices);
		

	}
	public void loginAsCustomer( LoginManager loginManager,CustomerServices customerServices) {
			customerServices = (CustomerServices) loginManager.login("nooreasa@gmail.com", "4567", ClientType.Customer);
			Company company=new Company(0, "addidas","addidas@ad.ac.il","A123f",null);

			Coupon coupon = new Coupon(0,company,null,Category.Fashion,"discount" ,"birthday discount",Date.valueOf("2017-12-12") ,Date.valueOf("2021-12-12"), 400, 20.0, "sdfa");  

			//purchase coupon by customer
			customerServices.addCouponPurchase(coupon);

			//get a list of customer coupons 
			System.out.println(customerServices.getCustomerCoupons());

			//print customer coupons by category
			System.out.println(customerServices.getCustomerCoupons(Category.Sport));

			//print customer coupons up to max price
			System.out.println(customerServices.getCustomerCoupons(100));


			//print customer details
			System.out.println(customerServices.getCustomerDetails());
		

	}
	private void loginAsCompany(LoginManager loginManager, CompanyServices companyServices){

			companyServices = (CompanyServices) loginManager.login("castro@ca.co.il", "1357", ClientType.Company); 
			Coupon coupon = new Coupon(0,null,null,Category.Fashion,"discount" ,"birthday discount",Date.valueOf("2017-12-12") ,Date.valueOf("2021-12-12"), 400, 20.0, "sdfa");  

			//add coupon
			companyServices.addCoupon(coupon);

			//update coupon 
			coupon.setAmount(33);
			companyServices.updateCoupon(coupon);

			//delete coupon
			companyServices.deleteCoupon(0);

			//print Company Coupons
			System.out.println(companyServices.getCompanyCoupons());

			//print coupons by Category
			System.out.println(companyServices.getCompanyCoupons(Category.Fashion));

			//print coupons up to max price
			System.out.println(companyServices.getCompanyCoupons(100));

			//print company details
			System.out.println(companyServices.getCompanyDetails());
	
	}
	public void loginAsAdmin(LoginManager loginManager ,AdminServices adminServices) {

		adminServices =(AdminServices) loginManager.login("admin@admin.com", "admin", ClientType.Adminstator);

		//add new company
		Company company=new Company(0, "addidas","addidas@ad.ac.il","A123f",null);
		adminServices.addCompany(company);

		company.setEmail("ad@adidas.ac.il");
		System.out.println(company.toString());
		//update company info
		adminServices.updateCompany(company);
		System.out.println(adminServices.getOneCompany(company.getId()));

		//delete Company
		adminServices.deleteCompany(0);

		//print all companies
		System.out.println(adminServices.getAllCompanies());

		Customer customer =new Customer( 0, "noor", "taha", "noort@gmail.com","2512",null);

		//add customer
		adminServices.addCustomer(customer);

		//update customer
		customer.setLastName("tahaa");
		adminServices.updateCustomer(customer);

		//delete customer
		adminServices.deleteCustomer(5);

		//print all customers
		System.out.println(adminServices.getAllCustomers());

		//print one customer
		System.out.println(adminServices.getOneCustomer(4));

	}
	   
	
}
