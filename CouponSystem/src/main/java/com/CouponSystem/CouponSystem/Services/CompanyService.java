package com.CouponSystem.CouponSystem.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.CouponSystem.Entities.ClientType;
import com.CouponSystem.CouponSystem.Entities.Company;
import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.CouponType;
import com.CouponSystem.CouponSystem.Exceptions.MyCouponSystemException;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;
import com.CouponSystem.CouponSystem.Repositories.CompanyRepository;
import com.CouponSystem.CouponSystem.Repositories.CouponRepository;

@Service
public class CompanyService implements CouponClientService {
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CompanyRepository companyRepository;
	private long currentCompanyId;
	public ClientType type = ClientType.Company;

	public CompanyService() {
	}

	/**
	 * A function which tries to create a new coupon for the current company
	 * @param coupon    The coupon that the company wishes to buy.
	 * @param companyId Id of the company which creates the coupon.
	 * @throws MyCouponSystemException Can happen when the coupon title is the same as
	 *                               another one in DB.
	 * @return True if coupon was created.
	 *
	 * 
	 */
	public boolean createCoupon(Coupon coupon, long companyId) throws MyCouponSystemException {

		boolean wasCreated = false;
		List<Coupon> allCoupons = couponRepository.findAll();
		boolean isAvailable = true;
		for (Coupon exsitingCoupon : allCoupons) {
			if (exsitingCoupon.getTitle().equals(coupon.getTitle())) {
				System.out.println("coupon title is the same as another coupon");
				isAvailable = false;
				throw new MyCouponSystemException("coupon title is the same as another coupon");
			}
		}
		if (isAvailable) {
			Company currentCompany = getCurrentCompany(companyId);
			Coupon tempCoup =	couponRepository.save(coupon);
			tempCoup.setCompany(currentCompany);
			currentCompany.addCoupon(tempCoup);
			companyRepository.save(currentCompany);
			wasCreated = true;
		}
		return wasCreated;
	}

	/**
	 * A function which receives a coupon and deletes it.
	 * 
	 * @param coupon    The coupon which will be removed.
	 * @param companyId Id of the company whose coupon will be removed.
	 * @throws MyCouponSystemException Can happen when getting data from DB has
	 *                               failed.
	 * @return True if the coupon was removed.
	 */
	public boolean removeCoupon(Coupon coupon, long companyId) throws MyCouponSystemException {
		System.out.println("remove coupon");
		boolean wasRemoved = false;
		Company currentCompany = getCurrentCompany(companyId);
		currentCompany.removeCoupon(coupon);
		coupon.setCompany(null);
		coupon.setCustomers(null);
		companyRepository.save(currentCompany);
		couponRepository.save(coupon);
		couponRepository.delete(coupon);
		wasRemoved = true;
		return wasRemoved;
	}

	/**
	 * A function which updates an existing coupon price and end date.
	 * 
	 * @param coupon The coupon which will be updated.
	 * @throws MyCouponSystemException Can happen when received coupon id is null.
	 * @return True if the coupon was updated.
	 */
	public boolean updateCoupon(Coupon coupon) throws MyCouponSystemException {
		boolean wasUpdated = false;
		Coupon oldCoupon;
		try {
			oldCoupon = couponRepository.findById(coupon.getId()).get();
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Coupon Id is null");
		}
		oldCoupon.setPrice(coupon.getPrice());
		oldCoupon.setEndDate(coupon.getEndDate());
		couponRepository.updateCoupon(oldCoupon);
		wasUpdated = true;
		return wasUpdated;
	}

	/**
	 * A function which receives an id and returns the corresponding coupon.
	 * 
	 * @param couponId An id of a coupon to get.
	 * @return a Coupon object that with the same id.Null if Couldn't get the
	 *         coupon.
	 * @throws MyCouponSystemException Can happen if the received id is null.
	 */
	public Coupon getCoupon(long couponId) throws MyCouponSystemException {
		Coupon coupon;
		try {
			coupon = couponRepository.findById(couponId).get();
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Coupon Id is null");
		}
		return coupon;
	}

	/**
	 * A function which returns all of the company coupons.
	 * 
	 * @param companyId Id of the company whose coupons to get
	 * @return An ArrayList of all of the company coupons.
	 */
	public ArrayList<Coupon> getAllCoupons(long companyId) {
		ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) couponRepository.findByCompanyId(currentCompanyId);
		return companyCoupons;
	}

	/**
	 * A function which returns the company coupons of the same type.
	 * 
	 * @param type      the type of the coupons to get.
	 * @param companyId Id of the company whose coupons to get.
	 * @return An ArrayList of the company coupons.
	 */
	public ArrayList<Coupon> getAllCouponsByType(CouponType type, long companyId) {
		ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) couponRepository.findByCompanyIdAndType(currentCompanyId, type);
		return companyCoupons;
	}

	/**
	 * A function which returns the company coupons with the same end date.
	 * 
	 * @param companyId Id of the company whose coupons to get.
	 * @param date      The date to filter the coupons (up-to the date)
	 * @return An ArrayList of all of the company coupons up-to the date.
	 */
	public ArrayList<Coupon> getAllCouponsByDate(LocalDate date, long companyId) {
		ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) couponRepository.findByCompanyIdAndEndDateLessThanEqual(currentCompanyId, Date.valueOf(date));
		return companyCoupons;
	}

	/**
	 * A function which returns the company coupons with the same price.
	 * 
	 * @param price The price to filter the coupons (up-to the price)
	 * @param companyId Id of the company whose coupons to get.
	 * @return An ArrayList of all of the company coupons up-to the price.
	 * @throws MyCouponSystemException Can happen if either the price or the received
	 *                               Id is null.
	 */
	public ArrayList<Coupon> getAllCouponsByPrice(double price, long companyId) throws MyCouponSystemException {
		ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) couponRepository.findByCompanyIdAndPriceLessThanEqual(currentCompanyId, price);
		return companyCoupons;
	}

	/**
	 * A function which returns the company with the given Id.
	 * 
	 * @param companyId Id of the company to get.
	 * @return A Company object which represent current connected company.
	 * @throws MyCouponSystemException Can happen if the received Id is null.
	 * 
	 */
	public Company getCurrentCompany(long companyId) throws MyCouponSystemException {
		Company company;
		try {
			company = companyRepository.findById(currentCompanyId).get();
			company.setCoupons(this.getAllCoupons(companyId));
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Company Id is null");
		}
		return company;
	}

	/**
	 * A function to check for login info of different companies.
	 * 
	 * @param name     Name of the company.
	 * @param password Password of the company.
	 * @return the company id if the login info is correct.
	 * @throws LoginException Can happen when there is no company with the same name and password in DB.
	 */
	public long login(String name, String password) throws LoginException {
		Company company = companyRepository.findByCompNameAndPassword(name, password);
		if (company != null) {
			currentCompanyId = company.getId();
			return company.getId();
		} else
			throw new LoginException("Didnt find any company with same name and password");
	}
}
