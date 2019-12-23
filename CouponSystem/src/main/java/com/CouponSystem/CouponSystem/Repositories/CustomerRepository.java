package com.CouponSystem.CouponSystem.Repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.CouponType;
import com.CouponSystem.CouponSystem.Entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	/**
	 * Find all coupons of a customer
	 * @param customer A customer to get all coupons of.
	 * @return All coupons of the requested customer.
	 */
	List<Coupon> findAllByCoupons(Customer customer);
	
	/**
	 * Find all coupons of a customer that has the requested type
	 * @param coupons Coupons of the customer
	 * @param type Type to filter by.
	 * @return A list of all the coupons filtered.
	 */
	List<Coupon> findAllByCouponsAndCoupons_type(Collection<Coupon> coupons,CouponType type);
	/**
	 * Find all coupons of a customer with price less than or equal to requestedPrice
	 * @param coupons Coupons of the customer
	 * @param price Price to filter by.
	 * @return A list of all the coupons filtered.
	 */
	List<Coupon> findAllByCouponsAndCoupons_priceLessThan(Collection<Coupon> coupons,Double price);
	/**
	 * Find a customer by its name and password.
	 * @param name The customer's name
	 * @param password The customer's password
	 * @return The requested customer.
	 */
	Customer findByCustNameAndPassword(String name,String password);
	@Transactional
	@Modifying
	@Query("update Customer c set c.password= :#{#customer.getPassword()} where c.id = :#{#customer.getId()}")
	public void updateCustomer(@Param("customer") Customer customer);
	
}
