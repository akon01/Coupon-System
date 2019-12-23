package com.CouponSystem.CouponSystem.RestControllers;

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
import com.CouponSystem.CouponSystem.Entities.Customer;
import com.CouponSystem.CouponSystem.Exceptions.MyCouponSystemException;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;
import com.CouponSystem.CouponSystem.Services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController extends ClientController {
	@Autowired
	private AdminService adminService;

	/**
	 * A function which tries to create a new company in the DB.
	 * 
	 * @param session The session of the connected admin.
	 * @param company A company to try to create.
	 * @return True if the company was created.
	 * @throws MyCouponSystemException Can happen if there is already a company with
	 *                               the same name.
	 */
	@RequestMapping(path = "CreateCompany", method = RequestMethod.POST)
	public boolean createCompany(HttpSession session, @RequestBody Company company) throws MyCouponSystemException {
		boolean isCreated = adminService.createCompany(company);
		return isCreated;
	}

	/**
	 * Removes a Company with all of its coupons and all of the linked coupons of
	 * that company.
	 * 
	 * @param session The session of the connected admin.
	 * @param company A company to try to remove.
	 * @return True if the company was removed.
	 * @throws MyCouponSystemException Can happen when the received company wasn't in
	 *                               the DB.
	 */
	@RequestMapping(path = "RemoveCompany", method = RequestMethod.POST)
	public boolean removeCompany(HttpSession session, @RequestBody Company company) throws MyCouponSystemException {
		boolean wasRemoved = false;
		wasRemoved = adminService.removeCompany(company);
		return wasRemoved;
	}

	/**
	 * Updates a company password and Email.
	 * 
	 * @param session The session of the connected admin.
	 * @param company A company to try to update.
	 * @return True if the company was updated.
	 * @throws MyCouponSystemException Can happen when the received company wasn't in
	 *                               the DB.
	 */
	@RequestMapping(path = "UpdateCompany", method = RequestMethod.POST)
	public boolean updateCompany(HttpSession session, @RequestBody Company company) throws MyCouponSystemException {
		boolean wasUpdated = false;
		wasUpdated = adminService.updateCompany(company);
		return wasUpdated;
	}

	/**
	 * Removes a Coupon and all of its links to customers and Companies.
	 * 
	 * @param session The session of the connected admin.
	 * @param coupon  A coupon to try to remove.
	 * @return True if the coupon was removed.
	 * @throws MyCouponSystemException Can happen when the received coupon wasn't in
	 *                               the DB.
	 */
	@RequestMapping(path = "RemoveCoupon", method = RequestMethod.POST)
	public boolean removeCoupon(HttpSession session, @RequestBody Coupon coupon) throws MyCouponSystemException {
		boolean wasRemoved = false;
		wasRemoved = adminService.removeCoupon(coupon);
		return wasRemoved;
	}

	/**
	 * Returns a Company from the DB by its Id.
	 * 
	 * @param session The session of the connected admin.
	 * @param id      The id of the company to try and get from the DB.
	 * @return The company from the DB.
	 * @throws MyCouponSystemException Can happen when the received id is null
	 */
	@RequestMapping(path = "GetCompany", method = RequestMethod.GET)
	public Company getCompany(HttpSession session, @RequestParam Long id) throws MyCouponSystemException {
		System.out.println(id);
		Company company = null;
		company = adminService.getCompany(id);
		return company;
	}

	/**
	 * Returns all companies.
	 * 
	 * @param session The session of the connected admin.
	 * @return An ArrayList of companies from the DB.
	 */
	@RequestMapping(path = "GetAllCompanies", method = RequestMethod.GET)
	public ArrayList<Company> getAllCompanies(HttpSession session) {
		ArrayList<Company> companies = new ArrayList<Company>();
		companies = adminService.getAllCompanies();
		return companies;
	}

	/**
	 * Deletes all companies.
	 * 
	 * @param session The session of the connected admin.
	 * @return True if all the companies were removed.
	 * @throws MyCouponSystemException Can happen when there are no companies to
	 *                               remove.
	 */
	@RequestMapping(path = "RemoveAllCompanies", method = RequestMethod.POST)
	public boolean removeAllCompanies(HttpSession session) throws MyCouponSystemException {
		boolean wereRemoved = false;
		wereRemoved = adminService.removeAllCompanies();
		return wereRemoved;
	}

	/**
	 * Deletes all customers.
	 * 
	 * @param session The session of the connected admin.
	 * @return True if all the customers were removed.
	 * @throws MyCouponSystemException Can happen when there aren't any customers to
	 *                               remove.
	 */
	@RequestMapping(path = "RemoveAllCustomers", method = RequestMethod.POST)
	public boolean removeAllCustomers(HttpSession session) throws MyCouponSystemException {
		boolean wereRemoved = false;
		wereRemoved = adminService.removeAllCustomers();
		return wereRemoved;
	}

	/**
	 * Deletes all coupons.
	 * 
	 * @param session The session of the connected admin.
	 * @return True if all the coupons were removed.
	 * @throws MyCouponSystemException Can happen when there aren't any coupons to
	 *                               remove.
	 */
	@RequestMapping(path = "RemoveAllCoupons", method = RequestMethod.POST)
	public boolean removeAllCoupons(HttpSession session) throws MyCouponSystemException {
		boolean wereRemoved = false;
		wereRemoved = adminService.removeAllCoupons();
		return wereRemoved;
	}

	/**
	 * Get a coupon by its Id from the DB.
	 * 
	 * @param session The session of the connected admin.
	 * @param id      The id of the requested coupon.
	 * @return The requested coupon from the DB.
	 */
	@RequestMapping(path = "GetCoupon", method = RequestMethod.GET)
	public Coupon getCoupon(HttpSession session, @RequestParam long id) {
		Coupon coupon = null;
		coupon = adminService.getCouponById(id);
		return coupon;
	}

	/**
	 * Creates a new customer. cannot create a customer with the same name as an
	 * existing one.
	 * 
	 * @param session  the session of the connected admin.
	 * @param customer A customer to try to create.
	 * @return True if the customer was created.
	 * @throws MyCouponSystemException Can happen when the new customer name already
	 *                               exists in the DB.
	 */
	@RequestMapping(path = "CreateCustomer", method = RequestMethod.POST)
	public boolean createCustomer(HttpSession session, @RequestBody Customer customer) throws MyCouponSystemException {
		boolean wasCreated = false;
		wasCreated = adminService.createCustomer(customer);
		return wasCreated;
	}

	/**
	 * Deletes an existing customer.
	 * 
	 * @param session  the session of the connected admin.
	 * @param customer A customer to try to remove.
	 * @return True if the customer was removed.
	 * @throws MyCouponSystemException Can happen when the customer to delete does not
	 *                               exist in DB.
	 */
	@RequestMapping(path = "RemoveCustomer", method = RequestMethod.POST)
	public boolean removeCustomer(HttpSession session, @RequestBody Customer customer) throws MyCouponSystemException {
		boolean wasRemoved = false;
		wasRemoved = adminService.removeCustomer(customer);
		return wasRemoved;
	}

	/**
	 * Updates a customers password.
	 * 
	 * @param session  the session of the connected admin.
	 * @param customer A customer to try to update.
	 * @return True if the customer was updated.
	 * @throws MyCouponSystemException Can happen when the received customer id is
	 *                               null.
	 */
	@RequestMapping(path = "UpdateCustomer", method = RequestMethod.POST)
	public boolean updateCustomer(HttpSession session, @RequestBody Customer customer) throws MyCouponSystemException {
		boolean wasUpdated = false;
		wasUpdated = adminService.updateCustomer(customer);
		return wasUpdated;
	}

	/**
	 * Returns a customer by id.
	 * 
	 * @param session The session of the connected admin.
	 * @param id      The Id of the requested customer.
	 * @return The requested customer from the DB.
	 * @throws MyCouponSystemException Can happen when the received customer id is
	 *                               null.
	 */
	@RequestMapping(path = "GetCustomer", method = RequestMethod.GET)
	public Customer getCustomer(HttpSession session, @RequestParam long id) throws MyCouponSystemException {
		Customer customer = null;
		customer = adminService.getCustomer(id);
		return customer;
	}

	/**
	 * Returns all available customers
	 * 
	 * @param session The session of the connected admin.
	 * @return An ArrayList of all available Customers.
	 */
	@RequestMapping(path = "/GetAllCustomers", method = RequestMethod.GET)
	public ArrayList<Customer> getAllCustomers(HttpSession session) {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers = adminService.getAllCustomers();
		return customers;
	}

	/**
	 * Returns all available coupons
	 * 
	 * @param session The session of the connected admin.
	 * @return An ArrayList of all available coupons.
	 */
	@RequestMapping(path = "GetAllCoupons", method = RequestMethod.GET)
	public ArrayList<Coupon> getAllCoupons(HttpSession session) {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		coupons = adminService.getAllCoupons();
		return coupons;
	}

	/**
	 * A function to check for login info of the admin
	 * 
	 * @param name     Name of the admin.
	 * @param password Password of the admin.
	 * @return True if login was successful.
	 * @throws LoginException Can happen when the name and password are not correct.
	 */
	@Override
	@RequestMapping(path = "/Login/{name}/{password}", method = RequestMethod.GET)
	public boolean login(HttpServletRequest request, @PathVariable("name") String name, @PathVariable("password") String password) throws LoginException {
		long id = adminService.login(name, password);
		if (id == 1l) {
			HttpSession session = request.getSession(true);
			session.setAttribute("type", ClientType.Admin);
			return true;
		} else {
			return false;
		}
	}
}
