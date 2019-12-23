package com.CouponSystem.CouponSystem.RestControllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.CouponSystem.Entities.ClientType;
import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.CouponType;
import com.CouponSystem.CouponSystem.Entities.Customer;
import com.CouponSystem.CouponSystem.Exceptions.MyCouponSystemException;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;
import com.CouponSystem.CouponSystem.Services.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController extends ClientController {
	@Autowired
	private CustomerService customerService;

	/**
	 * a function which receives a Coupon object and tries to buy one.
	 * 
	 * @param coupon  The coupon that the customer wishes to buy.
	 * @param session The session of the connected customer.
	 * @return True if the coupon was purchased.
	 * @throws MyCouponSystemException Can happen in any of these conditions: the
	 *                               coupon has expired,not enough left of the
	 *                               coupon
	 * 
	 */
	@RequestMapping(path = "purchase", method = RequestMethod.POST)
	public boolean purchaseCoupon(HttpSession session, @RequestBody Coupon coupon) throws MyCouponSystemException {
		boolean wasPurchased = false;
		wasPurchased = customerService.purchaseCoupon(coupon, (long) session.getAttribute("id"));
		return wasPurchased;
	}

	/**
	 * A function which returns all of the customer coupons in a Collection.
	 * 
	 * @param session The session of the connected customer.
	 * @return A Collection of all the purchased coupons the customer has.
	 * @throws MyCouponSystemException Can happen when received Id is null.
	 */
	@RequestMapping(path = "MyCoupons", method = RequestMethod.GET)
	public Collection<Coupon> getAllPurchasedCoupons(HttpSession session) throws MyCouponSystemException {
		return customerService.getAllPurchasedCoupons((long) session.getAttribute("id"));
	}

	/**
	 * A function which gets a type and id and returns all of the purchased coupons
	 * of that customer with only of that type.
	 * 
	 * @param type    A coupon type from CouponType enum.
	 * @param session The session of the connected customer.
	 * @return An ArrayList of all the customer owned coupons of the type.
	 * @throws MyCouponSystemException Can happen when getting customer info has failed.
	 */
	@RequestMapping(path = "MyCouponsByType", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllPurchasedCouponsByType(HttpSession session, @RequestParam String type) throws MyCouponSystemException {
		CouponType couponType = CouponType.valueOf(type);
		ArrayList<Coupon> customerCoupons = new ArrayList<Coupon>();
		customerCoupons = customerService.getAllPurchasedCouponsByType(couponType, (long) session.getAttribute("id"));
		return customerCoupons;
	}

	/**
	 * A function which returns an ArrayList of only the owned coupons up-to
	 * received price.
	 * 
	 * @param price   The price to filter the coupons (up-to the price)
	 * @param session The session of the connected customer.
	 * @return An ArrayList of all the coupons a customer owns with the same price.
	 * @throws MyCouponSystemException Can happen when getting customer info has failed.
	 */
	@RequestMapping(path = "MyCouponsByPrice", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllPurchasedCouponsByPrice(HttpSession session, double price) throws MyCouponSystemException {
		ArrayList<Coupon> customerCoupons = new ArrayList<Coupon>();
		customerCoupons = customerService.getAllPurchasedCouponsByPrice(price, (long) session.getAttribute("id"));
		return customerCoupons;
	}

	/**
	 * A function which returns all coupons of all companies.
	 * 
	 * @param session The session of the connected customer.
	 * @return An ArrayList of all available coupons
	 */
	@RequestMapping(path = "AvailableCoupons", method = RequestMethod.GET)
	public ArrayList<Coupon> getAvailableCoupon(HttpSession session) {
		ArrayList<Coupon> availableCoupons = new ArrayList<Coupon>();
		availableCoupons = customerService.getAvailableCoupons();
		return availableCoupons;
	}

	/**
	 * A function to get a customer by id.
	 * 
	 * @return A customer from the DB with the received Id.
	 * @param session The session of the connected customer.
	 * @throws MyCouponSystemException Can happen when the received id is null
	 */
	@RequestMapping(path = "MyInfo", method = RequestMethod.GET)
	public Customer getCurrentCustomer(HttpSession session) throws MyCouponSystemException {
		return customerService.getCurrentCustomer((long) session.getAttribute("id"));
	}

	/**
	 * A function to check for login info of different customers.
	 * 
	 * @param name     Name of the customer.
	 * @param password Password of the customer.
	 * @return True if login was successful.
	 * @throws LoginException Can happen when there is no customer with the same
	 *                        name and password in DB.
	 */
	@Override
	@RequestMapping(path = "Login/{name}/{password}", method = RequestMethod.GET)
	public boolean login(HttpServletRequest request, @PathVariable("name") String name, @PathVariable("password") String password) throws LoginException {
		System.out.println("try login ");
		long customerId = customerService.login(name, password);
		if (customerId != -1l) {
			HttpSession session = request.getSession(true);
			session.setAttribute("type", ClientType.Customer);
			session.setAttribute("id", customerId);
			return true;
		} else {
			System.out.println("no customer found!");
		}
		return false;
	}
}
