package com.coupons_system.job;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.coupons_system.model.Coupon;
import com.coupons_system.repositories.CouponRepository;
import com.coupons_system.utils.MyException;


public class CouponExpirationDailyJob implements Runnable{
	final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private static CouponExpirationDailyJob INSTANCE;

	 CouponRepository coupRepo;
	  private CouponExpirationDailyJob() {        
	    }
	    
	    public static CouponExpirationDailyJob getInstance() {
	        if(INSTANCE == null) {
	            INSTANCE = new CouponExpirationDailyJob();
	        }
	        return INSTANCE;
	    }
	    

	@Override
	public void run() {
		Instant todayDate=ZonedDateTime.now().toInstant();
		List<Coupon> coupons;

		try {
			coupons = coupRepo.findAll();
			for(Coupon c: coupons) {
				Instant endDate = c.getEndDate().toInstant();

				if(endDate.isAfter(todayDate)) {
					coupRepo.deleteById(c.getId());
				}else
					throw new MyException("The coupon date does not expired!");
			}
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}

	}

	public void stop() {
		executor.shutdown();
	

	}
	
	public void deleteExpiredCoupons(ScheduledExecutorService executor) {

		//check every 24 hours
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(this, 2, 24, TimeUnit.HOURS);

		try {
			TimeUnit.MILLISECONDS.sleep(20000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	   
	}
}
