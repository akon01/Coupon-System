package com.CouponSystem.CouponSystem.Repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.CouponType;

@Repository 
public interface CouponRepository extends JpaRepository<Coupon, Long> {
	/**
	 * Send query to get all coupons of the requested type
	 * 
	 * @param type A coupon type to get all coupons of.
	 * @return All the coupons with the requested type
	 */
	List<Coupon> findByType(CouponType type);

	/**
	 * Send query to update a coupon price and end date
	 * 
	 * @param coupon A coupon to update
	 */
	@Transactional
	@Modifying
	@Query("update Coupon c set c.price= :#{#coupon.getPrice()}, c.endDate= :#{#coupon.getEndDate()} where c.id = :#{#coupon.getId()}")
	public void updateCoupon(@Param("coupon") Coupon coupon);

	/**
	 * Find all the coupons of a company by its id.
	 * 
	 * @param companyId The company id of the company owning the coupons.
	 * @return All coupons owned by the company.
	 */
	public List<Coupon> findByCompanyId(long companyId);

	/**
	 * Find all the coupons of a company by its id and a specific type.
	 * 
	 * @param companyId The company id of the company owning the coupons.
	 * @param type      A coupon type to get coupons of.
	 * @return All coupons owned by the company having the requested type.
	 */
	public List<Coupon> findByCompanyIdAndType(long companyId, CouponType type);

	/**
	 * Find all the coupons of a company by its id and also that their end date is
	 * at and before the requested end date.
	 * 
	 * @param companyId The company id of the company owning the coupons.
	 * @param endDate   A date that the coupon expires on.
	 * @return All coupons owned by the company having their date before the
	 *         requested date
	 */
	public List<Coupon> findByCompanyIdAndEndDateLessThanEqual(long companyId, Date endDate);

	/**
	 * Find all the coupons of a company by its id and also that their price is
	 * equal or less then the requested price
	 * 
	 * @param companyId The company id of the company owning the coupons.
	 * @param price     The price of the coupon
	 * @return ALl coupons owned by the company having their price equal or less
	 *         then the requested price
	 */
	public List<Coupon> findByCompanyIdAndPriceLessThanEqual(long companyId, Double price);
	// Error: List<Coupon> findByCouponName( String name );
//	@Query("select c from Coupon c where c.name like %?1%")
//	// ?1 = the first parameter of the method (keyword)
//	List<Coupon> getAllCouponsWithKeyword( String keyword );
}
