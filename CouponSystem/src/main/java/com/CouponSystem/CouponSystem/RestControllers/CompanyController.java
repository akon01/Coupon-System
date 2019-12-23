package com.CouponSystem.CouponSystem.RestControllers;

import java.time.LocalDate;
import java.util.ArrayList;

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
import com.CouponSystem.CouponSystem.Entities.Company;
import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.CouponType;
import com.CouponSystem.CouponSystem.Exceptions.MyCouponSystemException;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;
import com.CouponSystem.CouponSystem.Services.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController extends ClientController {
	@Autowired
	private CompanyService companyService;

	/**
	 * A function which tries to create a new coupon for the current company
	 * 
	 * @param session The session of the connected company.
	 * @param coupon  A coupon object to create.
	 * @return True if the coupon was created.
	 * @throws MyCouponSystemException Can happen when the coupon title is the same as
	 *                               another one in DB.
	 */
	@RequestMapping(path = "CreateCoupon", method = RequestMethod.POST)
	public boolean createCoupon(HttpSession session, @RequestBody Coupon coupon) throws MyCouponSystemException {
		boolean wasCreated = false;
		wasCreated = companyService.createCoupon(coupon, (long) session.getAttribute("id"));
		return wasCreated;
	}

	/**
	 * A function which receives a coupon and deletes it.
	 * 
	 * @param session The session of the connected company.
	 * @param coupon  The coupon which will be removed.
	 * @throws MyCouponSystemException Can happen when getting data from DB has
	 *                               failed.
	 * @return True if the coupon was removed.
	 */
	@RequestMapping(path = "RemoveCoupon", method = RequestMethod.POST)
	public boolean getAllPurchasedCoupons(HttpSession session, @RequestBody Coupon coupon) throws MyCouponSystemException {
		boolean wasRemoved = false;
		wasRemoved = companyService.removeCoupon(coupon, (long) session.getAttribute("id"));
		return wasRemoved;
	}

	/**
	 * A function which updates an existing coupon price and end date.
	 * 
	 * @param session The session of the connected company.
	 * @param coupon  The coupon which will be updated.
	 * @throws MyCouponSystemException Can happen when received coupon id is null.
	 * @return True if the coupon was updated.
	 */
	@RequestMapping(path = "UpdateCoupon", method = RequestMethod.POST)
	public boolean updateCoupon(HttpSession session, @RequestBody Coupon coupon) throws MyCouponSystemException {
		boolean wasUpdated = false;
		wasUpdated = companyService.updateCoupon(coupon);
		return wasUpdated;
	}

	/**
	 * A function which receives an id and returns the corresponding coupon.
	 * 
	 * @param session  The session of the connected company.
	 * @param couponId An id of a coupon to get.
	 * @return a Coupon object that with the same id.Null if Couldn't get the
	 *         coupon.
	 * @throws MyCouponSystemException Can happen if the received id is null.
	 */
	@RequestMapping(path = "GetCoupon", method = RequestMethod.GET)
	public Coupon getCoupon(HttpSession session, @RequestParam long couponId) throws MyCouponSystemException {
		return companyService.getCoupon(couponId);
	}

	/**
	 * A function which returns all of the company coupons.
	 * 
	 * @param session The session of the connected company.
	 * @return An ArrayList of all of the company coupons.
	 */
	@RequestMapping(path = "GetCouponTypes", method = RequestMethod.GET)
	public ArrayList<String> getCouponTypes(HttpSession session) {
		System.out.println("getCouponTypes");
		CouponType[] couponTypes = CouponType.values();
		ArrayList<String> types = new ArrayList<String>();
		for (CouponType couponType : couponTypes) {
			types.add(couponType.toString());
		}
		return types;
	}

	/**
	 * A function which returns all of the company coupons.
	 * 
	 * @param session The session of the connected company.
	 * @return An ArrayList of all of the company coupons.
	 */
	@RequestMapping(path = "GetAllCoupons", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllCoupons(HttpSession session) {
		ArrayList<Coupon> availableCoupons = new ArrayList<Coupon>();
		availableCoupons = companyService.getAllCoupons((long) session.getAttribute("id"));
		return availableCoupons;
	}

	/**
	 * A function which returns the company coupons of the same type.
	 * 
	 * @param type    the type of the coupons to get.
	 * @param session The session of the connected company.
	 * @return An ArrayList of the company coupons.
	 */
	@RequestMapping(path = "GetAllCouponsByType", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllCouponsByType(HttpSession session, @RequestParam String type) {
		CouponType couponType = CouponType.valueOf(type);
		ArrayList<Coupon> availableCoupons = new ArrayList<Coupon>();
		availableCoupons = companyService.getAllCouponsByType(couponType, (long) session.getAttribute("id"));
		return availableCoupons;
	}

	/**
	 * A function which returns the company coupons with the same end date.
	 * 
	 * @param session The session of the connected company.
	 * @param price    The price to filter the coupons (up-to)
	 * @throws MyCouponSystemException Can happen if either the price or the receivedId is null.
	 * @return An ArrayList of all of the company coupons up-to the date.
	 */
	@RequestMapping(path = "GetAllCouponsByPrice", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllCouponsByPrice(HttpSession session, @RequestParam double price) throws MyCouponSystemException {
		ArrayList<Coupon> availableCoupons = new ArrayList<Coupon>();
		availableCoupons = companyService.getAllCouponsByPrice(price, (long) session.getAttribute("id"));
		return availableCoupons;
	}

	/**
	 * A function which returns the company coupons with the same end date.
	 * 
	 * @param session The session of the connected company.
	 * @param date    The date to filter the coupons (up-to the date)
	 * @return An ArrayList of all of the company coupons up-to the date.
	 */
	@RequestMapping(path = "GetAllCouponsByDate", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllCouponsByDate(HttpSession session, @RequestParam String date) {
		LocalDate couponDate = LocalDate.parse(date);
		ArrayList<Coupon> availableCoupons = new ArrayList<Coupon>();
		availableCoupons = companyService.getAllCouponsByDate(couponDate, (long) session.getAttribute("id"));
		return availableCoupons;
	}

	/**
	 * A function which returns the company with the given Id.
	 * 
	 * @param session The session of the connected company.
	 * @return A Company object which represent current connected company.
	 * @throws MyCouponSystemException Can happen if the received Id is null.
	 * 
	 */
	@RequestMapping(path = "MyInfo", method = RequestMethod.GET)
	public Company getCurrentCompany(HttpSession session) throws MyCouponSystemException {
		return companyService.getCurrentCompany((long) session.getAttribute("id"));
	}

	/**
	 * A function to check for login info of different companies.
	 * 
	 * @param name     Name of the company.
	 * @param password Password of the company.
	 * @return True if login was successful.
	 * @throws LoginException Can happen when there is no company with the same name
	 *                        and password in DB.
	 */
	@Override
	@RequestMapping(path = "Login/{name}/{password}", method = RequestMethod.GET)
	public boolean login(HttpServletRequest request, @PathVariable("name") String name, @PathVariable("password") String password) throws LoginException {
		System.out.println("try login ");
		long customerId = companyService.login(name, password);
		if (customerId != -1l) {
			HttpSession session = request.getSession(true);
			session.setAttribute("type", ClientType.Company);
			session.setAttribute("id", customerId);
			return true;
		} else {
			return false;
		}
	}
}
