package com.CouponSystem.CouponSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.CouponSystem.CouponSystem.Entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	/**
	 * Send query to update a company password and email.
	 * 
	 * @param company A company to update.
	 */
	@Transactional
	@Modifying
	@Query("update Company c set c.password= :#{#company.getPassword()}, c.email= :#{#company.getEmail()} where c.id = :#{#company.getId()}")
	public void updateCompany(Company company);

	/**
	 * Send query to get the company id by its name and password
	 * 
	 * @param name     The company name
	 * @param password The company password.
	 * @return The id of the requested company
	 */
	@Query("select c.id from Company c where c.compName =?1 and c.password= ?2")
	public int getLogin(String name, String password);

	/**
	 * Send query to get the company details by its name and password
	 * 
	 * @param name     The company name
	 * @param password The company password.
	 * @return The selected company object
	 */
	Company findByCompNameAndPassword(String name, String password);
}
