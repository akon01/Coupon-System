package com.CouponSystem.CouponSystem.Entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String compName, password;
	@Email
	private String email;
	//@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "company",cascade= CascadeType.REMOVE)//, orphanRemoval = true
	private Collection<Coupon> coupons = new ArrayList<Coupon>();

	public Company() {
		super();
	}

	/**
	 * Construct a new Company object.
	 * 
	 * @param id       The new company id.
	 * @param compName The new company name.
	 * @param password The new company password.
	 * @param email    The new company email.
	 * @param coupons  An ArrayList of the new company's Coupons.
	 */
	public Company(long id, String compName, String password, String email, ArrayList<Coupon> coupons) {
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}

	/**
	 * Constructs a new company but without an id.
	 * 
	 * @param compName The new company name.
	 * @param password The new company password.
	 * @param email    The new company email.
	 * @param coupons  An ArrayList of the new comapny's Coupons.
	 */
	public Company(String compName, String password, String email, ArrayList<Coupon> coupons) {
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}

	/**
	 * Sets this company's password.
	 * 
	 * @param password A new password for the company.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns current company id.
	 * 
	 * @return current company id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns current company name.
	 * 
	 * @return current company name.
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * Returns current company password.
	 * 
	 * @return current company password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns current company email.
	 * 
	 * @return current company email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns all of the current company's Coupons.
	 * 
	 * @return An ArrayList of Coupons.
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * Add a coupon to the coupons created by this company.
	 * 
	 * @param coupon A coupon to add.
	 */
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

	/**
	 * Remove a coupon from the coupons created by this company.
	 * 
	 * @param coupon A coupon to remove.
	 */
	public void removeCoupon(Coupon coupon) {
		this.coupons.remove(coupon);
	}

	/**
	 * Sets current company's Coupons
	 * 
	 * @param coupons An ArrayList of Coupons.
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons.clear();
		this.coupons =coupons;
	}

	/**
	 * Sets current company email.
	 * 
	 * @param email new mail to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Company (id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + ")"  + ")";//+ ", coupons=" + coupons.toString()
	}
}
