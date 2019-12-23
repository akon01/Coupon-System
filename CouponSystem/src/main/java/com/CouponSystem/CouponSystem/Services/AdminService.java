package com.CouponSystem.CouponSystem.Services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.CouponSystem.Entities.ClientType;
import com.CouponSystem.CouponSystem.Entities.Company;
import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.Customer;
import com.CouponSystem.CouponSystem.Exceptions.MyCouponSystemException;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;
import com.CouponSystem.CouponSystem.Repositories.CompanyRepository;
import com.CouponSystem.CouponSystem.Repositories.CouponRepository;
import com.CouponSystem.CouponSystem.Repositories.CustomerRepository;

@Service
public class AdminService implements CouponClientService {
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CompanyRepository companyRepository;
	public ClientType type = ClientType.Admin;

	public AdminService() {}


	/**
	 * A function which tries to create a new company.
	 * 
	 * @return True if the company was created successfully.
	 * @param company A Company object to create.
	 * @throws MyCouponSystemException Can happen when a company with the same name already exists.
	 */
	public boolean createCompany(Company company) throws MyCouponSystemException {
		ArrayList<Company> companies;
		companies = (ArrayList<Company>) companyRepository.findAll();
		boolean isAvailable = true;
		// Check to see if there already is a company with the same name.
		for (Company tempCompany : companies) {
			if ((tempCompany).getCompName().equals(company.getCompName())) {
				isAvailable = false;
				throw new MyCouponSystemException("Company with the same name already exists!");
				
			}
		}
		if (isAvailable) {
			companyRepository.save(company);
		}
		return isAvailable;
	}

	/**
	 * Removes a Company with all of its coupons and all of the linked coupons of that company.
	 * @return True if the company was removed.
	 * @param company A Company to remove.
	 * @throws MyCouponSystemException Can happen if received company does not exist in the DB.
	 */
	public boolean removeCompany(Company company) throws MyCouponSystemException {
		Collection<Coupon> customerCoupons = company.getCoupons();
		for (Coupon coupon : customerCoupons) {
//			coupon.setCustomers(null);
			Coupon tempCoupon;
			tempCoupon= couponRepository.findById(coupon.getId()).get();
//			couponRepository.save(coupon);
			this.removeCoupon(tempCoupon);
//			couponRepository.delete(coupon);
		}
		System.out.println(company.getCoupons());
		company.setCoupons(null);
		try {
		companyRepository.delete(company);
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Received company does not exist.");
		}	
		return true;
	}

	/**
	 * Updates a company password and Email.
	 * @return True if the company info was updated.
	 * @param company A company with updated info to update in DB.
	 * @throws MyCouponSystemException Can happens company Id is null.
	 */
	public boolean updateCompany(Company company) throws MyCouponSystemException  {
		Company oldCompany;
		try {
		oldCompany = companyRepository.findById(company.getId()).get();
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Company Id must not be null");
		}
		oldCompany.setEmail(company.getEmail());
		oldCompany.setPassword(company.getPassword());
		 companyRepository.updateCompany(oldCompany);
		 return true;
	}

	/**
	 * Removes a Coupon and all of its links to customers and Companies.
	 * 
	 * @return True if the coupon was removed.
	 * @param coupon A coupon to remove.
	 * @throws MyCouponSystemException Can happen if the received coupon does not exist in the DB.
	 */
	public boolean removeCoupon(Coupon coupon) throws MyCouponSystemException {
		try {
		couponRepository.delete(coupon);
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Received coupon does not exist");
		}
		return true;
	}

	/**
	 * Returns a Company from the DB by its Id.
	 * 
	 * @param id An ID of a company to get.
	 * @return A company with received ID. Null if Couldn't get the company.
	 * @throws MyCouponSystemException Can happen when the received id is null;
	 */
	public Company getCompany(long id) throws MyCouponSystemException  {
		Company company;
		 try {
		company = companyRepository.findById(id).get();
		 } catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Coupon Id must not be null");
		}
		 
		return company;
	}

	/**
	 * Returns all companies.
	 * 
	 * @return An ArrayList of all the companies. Null if Couldn't get the
	 *         companies.
	 */
	public ArrayList<Company> getAllCompanies()  {
		ArrayList<Company> companyList = (ArrayList<Company>) companyRepository.findAll();
		return companyList;
	}

	/**
	 * Deletes all companies.
	 * @return True if all the companies were removed.
	 * @throws MyCouponSystemException Can happen when there are no companies to remove.
	 */
	public boolean removeAllCompanies() throws MyCouponSystemException {
		ArrayList<Company> companies = this.getAllCompanies();
		boolean wereRemoved = false;
		if (!companies.isEmpty()) {
			companyRepository.deleteAll();
			wereRemoved = true;
		} else
			throw new MyCouponSystemException("No Companies to remove.");
		return wereRemoved;
	}

	/**
	 * Deletes all customers.
	 * @return True if all the customers were removed.
	 * @throws MyCouponSystemException Can happen when there aren't any customers to remove.
	 */
	public boolean removeAllCustomers() throws MyCouponSystemException {
		ArrayList<Customer> customers;
		boolean wereRemoved = false;

		customers = this.getAllCustomers();
		if (!customers.isEmpty()) {
			for (Customer customer : customers) {
				this.removeCustomer(customer);
			}
			wereRemoved = true;
		} else
			throw new MyCouponSystemException("No customers to remove.");
		return wereRemoved;
	}

	/**
	 * Deletes all coupons.
	 * @return True if all the coupons were removed.
	 * 
	 * @throws MyCouponSystemException Can happen when there aren't any coupons to remove
	 */
	public boolean removeAllCoupons() throws MyCouponSystemException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		boolean wereRemoved = false;
		coupons = this.getAllCoupons();
		if (!coupons.isEmpty()) {
			for (Coupon coupon : coupons) {
				this.removeCoupon(coupon);
			}
			wereRemoved = true;
		} else
			throw new MyCouponSystemException("No coupons to remove.");
		return wereRemoved;
	}

	/**
	 * Get a coupon by its Id from the DB.
	 * @param couponId The id of the required coupon.
	 * @return A coupon object from the DB.
	 */
	public Coupon getCouponById(long couponId) {
			return this.couponRepository.findById(couponId).get();
	}

	/**
	 * Creates a new customer. cannot create a customer with the same name as an
	 * existing one.
	 * @return True if the customer was created.
	 * @param customer A Customer to try to create.
	 * @throws MyCouponSystemException Can happen when the new customer name already exists in the DB.
	 */
	public boolean createCustomer(Customer customer) throws MyCouponSystemException  {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers = this.getAllCustomers();
		boolean isAvailable = true;
		// Check if there is already an existing customer with the same name.
		for (Customer tempCustomer : customers) {
			if (tempCustomer.getCustName().equals(customer.getCustName())) {
				isAvailable = false;
				throw new MyCouponSystemException("Customer name already exists in DB!");
			}
		}
		if (isAvailable) {
			customerRepository.save(customer);
			
		}
		return isAvailable;
	}

	/**
	 * Deletes an existing customer.
	 * @return True if the customer was removed
	 * @param customer A customer to delete.
	 * @throws MyCouponSystemException Can happen when the customer to delete does not exist in DB.
	 */
	public boolean removeCustomer(Customer customer) throws MyCouponSystemException {
		Customer cTemp = customerRepository.findById(customer.getId()).get();
		Collection<Coupon> customerCoupons = cTemp.getCoupons();
		for (Coupon coupon : customerCoupons) {
			coupon.removeCustomer(cTemp);	
			System.out.println(coupon.getCompany());
			coupon.setCompany( coupon.getCompany());
			couponRepository.save(coupon);
			
		}
		try {
		customerRepository.delete(customer);
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Received customer does not exist!");
		}
		return true;
	}

	/**
	 * Updates a customers password.
	 * @return True if the customer was updated.
	 * @param customer An existing customer with changed info to update.
	 * @throws MyCouponSystemException Can happen when the received customer id is null.
	 */
	public boolean updateCustomer(Customer customer) throws MyCouponSystemException {
		Customer oldCustomer;
		try {
		oldCustomer = customerRepository.findById(customer.getId()).get();
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Customer Id must not be null.");
		}
		oldCustomer.setPassword(customer.getPassword());
		customerRepository.updateCustomer(oldCustomer);
		return true;
	}

	/**
	 * Returns a customer by id.
	 * 
	 * @param id An existing customer id .
	 * @return A Customer object with the same id.
	 * @throws MyCouponSystemException Can happen when the received customer id is null.
	 */
	public Customer getCustomer(long id) throws MyCouponSystemException {
		Customer customer;
		try {
		customer = customerRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new MyCouponSystemException("Customer Id must not be null.");
		}
		return customer;
	}

	/**
	 * Returns all available customers
	 * 
	 * @return An ArrayList of all available Customers
	 */
	public ArrayList<Customer> getAllCustomers() {
		return (ArrayList<Customer>) customerRepository.findAll();
	}

	/**
	 * Returns all available coupons
	 * 
	 * @return An ArrayList of all available coupons.
	 */
	public ArrayList<Coupon> getAllCoupons() {
		return (ArrayList<Coupon>) couponRepository.findAll();
	}

	/**
	 * A function to check for login info of admin
	 * 
	 * @param name Name of the admin.
	 * @param password Password of the admin.
	 * @return 1, as the admin does not have id. 
	 * @throws LoginException Can happen when there is no customer with the same name and password in DB.
	 */
	@Override
	public long login(String name, String password) throws LoginException {
		if (name.equals("admin") && password.equals("1234")) {
			return 1l;
		} else
			throw new LoginException("Admin username and password are not correct!");
	}
}
