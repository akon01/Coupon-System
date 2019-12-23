package com.CouponSystem.CouponSystem.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.CouponSystem.Entities.ClientType;
import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.CouponType;
import com.CouponSystem.CouponSystem.Entities.Customer;
import com.CouponSystem.CouponSystem.Exceptions.MyCouponSystemException;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;
import com.CouponSystem.CouponSystem.Repositories.CouponRepository;
import com.CouponSystem.CouponSystem.Repositories.CustomerRepository;

@Service
public class CustomerService implements CouponClientService {
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerRepository customerRepository;
	public ClientType type = ClientType.Customer;

	public CustomerService() {
	}

	/**
	 * a function which receives a Coupon object and tries to buy one.
	 * 
	 * @param coupon     The coupon that the customer wishes to buy.
	 * @param customerId Id of the customer who will try to buy the coupon.
	 * @return True if the coupon was purchased.
	 * @throws MyCouponSystemException Can happen in any of these conditions: the
	 *                               coupon has expired,not enough left of the
	 *                               coupon
	 * 
	 */
	public boolean purchaseCoupon(Coupon coupon, long customerId) throws MyCouponSystemException {
		boolean wasPurchased = false;
		List<Coupon> allCoupons = couponRepository.findAll();
		Customer currentCustomer = this.getCurrentCustomer(customerId);
		for (Coupon exsitingCoupon : allCoupons) {
			if (exsitingCoupon.getId() == (coupon.getId())) {
				System.out.println(currentCustomer.getCoupons());
				if(!currentCustomer.getCoupons().contains(coupon)) {
				if (exsitingCoupon.getAmount() >= 1) {
					if (!exsitingCoupon.getEndDate().before(new Date())) {
						exsitingCoupon.setAmount(exsitingCoupon.getAmount() - 1);
						exsitingCoupon.addCustomer(currentCustomer);
						couponRepository.save(exsitingCoupon);
						currentCustomer.addCoupon(exsitingCoupon);
						customerRepository.save(currentCustomer);
						wasPurchased = true;
						break;
					} else {
						throw new MyCouponSystemException("Coupon has expierd");
					}
				} else {
					throw new MyCouponSystemException("Coupon amount is not enough");
				}
			}
		}
			}
		return wasPurchased;
	}

	/**
	 * A function which returns all of the customer coupons in a Collection.
	 * 
	 * @param customerId Id of the customer whose coupons to get.
	 * @return A Collection of all the purchased coupons the customer has.
	 * @throws MyCouponSystemException Can happen when received Id is null.
	 */
	public Collection<Coupon> getAllPurchasedCoupons(long customerId) throws MyCouponSystemException {
		Customer currentCustomer = this.getCurrentCustomer(customerId);
		Collection<Coupon> customerCoupons = currentCustomer.getCoupons();
		return customerCoupons;
	}

	/**
	 * A function which gets a type and id and returns all of the purchased coupons
	 * of that customer with only of that type.
	 * 
	 * @param type       A coupon type from CouponType enum.
	 * @param customerId Id of the customer whose coupons to get.
	 * @return An ArrayList of all the customer owned coupons of the type.
	 * @throws MyCouponSystemException Can happen when getting customer info has failed.
	 */
	public ArrayList<Coupon> getAllPurchasedCouponsByType(CouponType type, long customerId) throws MyCouponSystemException {
		Customer currentCustomer = this.getCurrentCustomer(customerId);
		ArrayList<Coupon> couponsByType = new ArrayList<Coupon>();
		Collection<Coupon> customerCoupons = currentCustomer.getCoupons();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getType() == type.toString()) {
				couponsByType.add(coupon);
			}
		}
		return couponsByType;
	}

	/**
	 * A function which returns an ArrayList of only the owned coupons up-to
	 * received price.
	 * 
	 * @param price      The price to filter the coupons (up-to the price)
	 * @param customerId Id of the customer whose coupons to get.
	 * @return An ArrayList of all the coupons a customer owns with the same price.
	 * @throws MyCouponSystemException Can happen when getting customer info has failed.
	 */
	public ArrayList<Coupon> getAllPurchasedCouponsByPrice(double price, long customerId) throws MyCouponSystemException {
		Customer currentCustomer = this.getCurrentCustomer(customerId);
		ArrayList<Coupon> couponsByPrice = new ArrayList<Coupon>();
		Collection<Coupon> customerCoupons = currentCustomer.getCoupons();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getPrice() <= price) {
				couponsByPrice.add(coupon);
			}
		}
		return couponsByPrice;
	}

	/**
	 * A function which returns all coupons of all companies.
	 * 
	 * @return An ArrayList of all available coupons
	 */
	public ArrayList<Coupon> getAvailableCoupons() {
		System.out.println("test!");
		List<Coupon> allCoupons = couponRepository.findAll();
		System.out.println("test2!");
		return (ArrayList<Coupon>) allCoupons;
	}

	/**
	 * A function to get a customer by id.
	 * 
	 * @return A customer from the DB with the received Id.
	 * @param customerId Id of the customer to get.
	 * @throws MyCouponSystemException Can happen when the received id is null
	 */
	public Customer getCurrentCustomer(long customerId) throws MyCouponSystemException {
		Customer customer;
		try {
			customer = customerRepository.findById(customerId).get();
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Customer Id is null");
		}
		return customer;
	}

	/**
	 * A function to check for login info of different customers.
	 * 
	 * @param name     Name of the customer.
	 * @param password Password of the customer.
	 * @return the customer id if the login info is correct.
	 * @throws LoginException Can happen when there is no customer with the same
	 *                        name and password in DB.
	 */
	public long login(String name, String password) throws LoginException {
		Customer customer = customerRepository.findByCustNameAndPassword(name, password);
		if (customer != null) {
			customer.getId();
			return customer.getId();
		} else
			throw new LoginException("Didnt find any customer with same name and password");
	}
}
