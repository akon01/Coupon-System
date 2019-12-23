package com.CouponSystem.CouponSystem.Tasks;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.CouponSystem.CouponSystem.Entities.Company;
import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Exceptions.MyCouponSystemException;
import com.CouponSystem.CouponSystem.Services.AdminService;

@Component
public class DailyCouponExpirationTask {
	@Autowired
	private AdminService adminService;
	boolean quit = false;

	public DailyCouponExpirationTask() {
		super();
	}

	/**
	 * A function that will delete every coupon which has expired each day at 00:00
	 * 
	 * @throws MyCouponSystemException Can happen if there was a problem with deleting
	 *                               a coupon.
	 */
	@Scheduled(cron = "0 00 00 1/1 * ?")
	public void runTask() throws MyCouponSystemException {
		if (!quit) {
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			ArrayList<Company> companies = new ArrayList<Company>();
			companies = adminService.getAllCompanies();
			for (Company company : companies) {
				coupons.addAll(company.getCoupons());
			}
			for (Coupon coupon : coupons) {
				if (LocalDate.now().isAfter(coupon.getEndDate().toLocalDate())) {
					adminService.removeCoupon(coupon);
				}
			}
		}
	}

	/*
	 * A function which stops the task and ends the thread.
	 */
	public void stopTask() {
		quit = !quit;
		Thread.currentThread().interrupt();
	}
}
