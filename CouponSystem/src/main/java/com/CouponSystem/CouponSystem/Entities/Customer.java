package com.CouponSystem.CouponSystem.Entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String custName, password;
	@ManyToMany(fetch = FetchType.EAGER,  mappedBy = "customers")
	private Collection<Coupon> coupons = new ArrayList<>();

	/**
	 * Creates a new Customer.
	 * 
	 * @param id       New customer ID.
	 * @param custName New customer name.
	 * @param password New customer password.
	 * @param coupons  New customer Coupons.
	 */
	public Customer(long id, String custName, String password, ArrayList<Coupon> coupons) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
		this.coupons = coupons;
	}

	/**
	 * Creates a new Customer without an ID.
	 * 
	 * @param custName New customer name.
	 * @param password New customer password.
	 * @param coupons  New customer Coupons.
	 */
	public Customer(String custName, String password, ArrayList<Coupon> coupons) {
		super();
		this.custName = custName;
		this.password = password;
		this.coupons = coupons;
	}

	public Customer() {
		super();
	}

	/**
	 * Sets current customer a new password.
	 * 
	 * @param password New password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets current customer's Coupons.
	 * 
	 * @param coupons An ArrayList of Coupons.
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons.clear();
		this.coupons.addAll(coupons);
	}

	/**
	 * Add a coupon to the coupon bought by this customer.
	 * 
	 * @param coupon A coupon to add.
	 */
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

	/**
	 * Remove a coupon from the coupons bought by this customer.
	 * 
	 * @param coupon A coupon to remove.
	 */
	public void removeCoupon(Coupon coupon) {
		this.coupons.remove(coupon);
	}

	/**
	 * Returns current customer ID.
	 * 
	 * @return Current customer ID.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns current customer name.
	 * 
	 * @return Current customer name.
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * Returns current customer password.
	 * 
	 * @return Current customer password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns current customer Coupons.
	 * 
	 * @return An ArrayList of the current customer's Coupons.
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	@Override
	public String toString() {
		return "Customer (id=" + id + ", custName=" + custName + ", password=" + password + ", coupons=" + coupons + ")";
	}
}
