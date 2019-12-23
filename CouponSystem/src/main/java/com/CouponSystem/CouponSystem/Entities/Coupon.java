package com.CouponSystem.CouponSystem.Entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title, message, image;
	private Date startDate, endDate;
	private int amount;
	private double price;
	private CouponType type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Company company;
	//@ManyToMany(fetch = FetchType.LAZY  )//
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private Collection<Customer> customers = new ArrayList<Customer>();

	/**
	 * Get the customers who bought this coupon
	 * 
	 * @return null. //
	 */
//	public Collection<Customer> getCustomers() {
//		return null;
//	}
	/**
	 * Sets the customers who bought this coupon.
	 * 
	 * @param customers A collection of customers who bought this coupon.
	 */
	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * Add customers to the customers who bought this coupon.
	 * 
	 * @param customers A collection of customers to add.
	 */
	public void addCustomers(Collection<Customer> customers) {
		this.customers.addAll(customers);
	}

	/**
	 * Add a customer to the customers who bought this coupon.
	 * 
	 * @param customer A customers to add.
	 */
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}

	/**
	 * Remove a customer from the customers who bought this coupon.
	 * 
	 * @param customer A customers to remove.
	 */
	public void removeCustomer(Customer customer) {
		this.customers.remove(customer);
	}
	

	/**
	 * Creates a new coupon.
	 * 
	 * @param id        New coupon ID.
	 * @param title     New coupon title.
	 * @param startDate New coupon start date.
	 * @param endDate   New coupon end date.
	 * @param amount    New coupon amount.
	 * @param type      new coupon type.
	 * @param message   New coupon message.
	 * @param price     New coupon price.
	 * @param image     New coupon image string
	 */
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.type = type;
	}

	/**
	 * 
	 * @param title     New coupon title.
	 * @param startDate New coupon start date.
	 * @param endDate   New coupon end date.
	 * @param amount    New coupon amount.
	 * @param type      new coupon type.
	 * @param message   New coupon message.
	 * @param price     New coupon price.
	 * @param image     New coupon image string
	 * @param company   the Company who owns the coupon.
	 */
	public Coupon(String title, String message, String image, Date startDate, Date endDate, int amount, double price, CouponType type, Company company) {
		super();
		this.title = title;
		this.message = message;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.type = type;
		this.company = company;
	}

	/**
	 * Creates a new coupon without an ID.
	 * 
	 * @param title     New coupon title.
	 * @param startDate New coupon start date.
	 * @param endDate   New coupon end date.
	 * @param amount    New coupon amount.
	 * @param type      new coupon type.
	 * @param message   New coupon message.
	 * @param price     New coupon price.
	 * @param image     New coupon image string
	 */
	public Coupon(String title, String message, String image, Date startDate, Date endDate, int amount, double price, CouponType type) {
		super();
		this.title = title;
		this.message = message;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.type = type;
	}

	public Coupon() {
		super();
	}

	/**
	 * set current coupon amount.
	 * 
	 * @param amount New amount for the coupon.
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Returns current coupon ID.
	 * 
	 * @return Current coupon ID.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns current coupon title.
	 * 
	 * @return Current coupon title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns current coupon message.
	 * 
	 * @return Current coupon message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns current coupon image string.
	 * 
	 * @return Current coupon image string.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Returns current coupon end date.
	 * 
	 * @return Current coupon end date.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Returns current coupon amount.
	 * 
	 * @return Current coupon amount.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Returns current coupon price.
	 * 
	 * @return Current coupon price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns current coupon type.
	 * 
	 * @return Current coupon type.
	 */
	public String getType() {
		return String.valueOf(type);
	}

	/**
	 * Returns current coupon start date.
	 * 
	 * @return Current coupon start date.
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets current coupon end date.
	 * 
	 * @param endDate A new end date to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Sets current coupon price.
	 * 
	 * @param price A new price to set.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Coupon (id=" + id + ", title=" + title + ", message=" + message + ", image=" + image + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount
				+ ", price=" + price + ", type=" + type + ")";
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
