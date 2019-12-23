package com.CouponSystem.CouponSystem;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.CouponSystem.CouponSystem.Entities.ClientType;
import com.CouponSystem.CouponSystem.Entities.Company;
import com.CouponSystem.CouponSystem.Entities.Coupon;
import com.CouponSystem.CouponSystem.Entities.CouponType;
import com.CouponSystem.CouponSystem.Entities.Customer;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;
import com.CouponSystem.CouponSystem.Services.AdminService;
import com.CouponSystem.CouponSystem.Services.CompanyService;
import com.CouponSystem.CouponSystem.Services.CustomerService;

@SpringBootApplication
@EnableScheduling
public class CouponSystemApplication {
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CouponSystemApplication.class, args);
		// Customer customer = (Customer) context.getBean("customer");
//		 AdminService adminService = (AdminService) context.getBean("adminService");
//		 adminService.createCompany(new Company("comp1", "comp1", "comp1", new ArrayList<Coupon>()));
//		 try {
//			adminService.createCustomer(new Customer("cust1", "cust1", new ArrayList<Coupon>()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// LoginManager loginManager =
		// (com.CouponSystem.CouponSystem.Entities.LoginManager)
		// context.getBean("loginManager");
		// loginManager.login(name, password, clientType);
		Scanner scan = new Scanner(System.in);
		int menuItem = 0;
		int adminItem = 0;
		int customerItem = 0;
		int companyItem = 0;
		boolean adminEnd = false;
		boolean customerEnd = false;
		boolean companyEnd = false;
		Company tempComp = null;
		Customer tempCust = null;
		Coupon tempCoup = null;
		
//		long id = (Long) null;
//		LoginManager loginManager = (com.CouponSystem.CouponSystem.Entities.LoginManager) context.getBean("loginManager");
//		try {
//			while (menuItem != 4) {
//				System.out.println("1.admin\n2.company\n3.customer\n4.exit");
//				menuItem = scan.nextInt();
//				if (menuItem == 1) {
//					System.out.println("enter admin name and password");
//					String name = scan.next(), pass = scan.next();
//					if(loginManager.login("admin"/* name */, "1234"/* pass */, ClientType.Admin) == 1) {
//						System.out.println("admin connected");
//					}
//					//AdminService admin = (AdminService) loginManager.login("admin"/* name */, "1234"/* pass */, ClientType.Admin);
//					if (admin != null) {
//						adminEnd = false;
//						while (!adminEnd) {
//							System.out.println(
//									"1.show all companies\n2.create company\n3.remove company\n4.update company info\n5.show company info\n6.create customer\n7.remove customer\n8.update customer info\n9.show all customers\n10.show customer info\n11.show all coupons\n12.delete all info (customers,companies,coupons)\n13.delete Coupon by id");
//							adminItem = scan.nextInt();
//							switch (adminItem) {
//							case 1:
//								System.out.println(admin.getAllCompanies());
//								break;
//							case 2:
//								System.out.println("enter new company info: name,pass,email ");
//								String compName = scan.next(), password = scan.next(), email = scan.next();
//								tempComp = new Company(compName, password, email, new ArrayList<Coupon>());
//								admin.createCompany(tempComp);
//								tempComp = null;
//								break;
//							case 3:
//								System.out.println("enter company id to remove: ");
//								for (Company company : admin.getAllCompanies()) {
//									System.out.println(company.getId() + ":" + (company.getCompName()));
//								}
//								for (int i = 1; i <= admin.getAllCompanies().size(); i++) {
//								}
//								tempComp = admin.getCompany(scan.nextInt());
//								if (tempComp != null) {
//									admin.removeCompany(tempComp);
//								} else
//									System.out.println("no Company with that id!");
//								tempComp = null;
//								break;
//							case 4:
//								System.out.println("enter company id and then info to update: ");
//								for (Company company : admin.getAllCompanies()) {
//									System.out.println(company.getId() + ":" + (company.getCompName()));
//								}
//								tempComp = admin.getCompany(scan.nextInt());
//								if (tempComp != null) {
//									System.out.println("enter new password: ");
//									tempComp.setPassword(scan.next());
//									System.out.println("enter new mail:");
//									tempComp.setEmail(scan.next());
//									admin.updateCompany(tempComp);
//								} else
//									tempComp = null;
//								break;
//							case 5:
//								System.out.println("enter company id to show info: ");
//								for (Company company : admin.getAllCompanies()) {
//									System.out.println(company.getId() + ":" + (company.getCompName()));
//								}
//								tempComp = admin.getCompany(scan.nextInt());
//								if (tempComp != null) {
//									System.out.println(tempComp);
//								} else
//									System.out.println("no company with that id!");
//								tempComp = null;
//								break;
//							case 6:
//								System.out.println("enter new customer info: ");
//								System.out.println("enter username: ");
//								String custName = scan.next();
//								System.out.println("enter password");
//								String custPassword = scan.next();
//								tempCust = new Customer(custName, custPassword, new ArrayList<Coupon>());
//								admin.createCustomer(tempCust);
//								tempCust = null;
//								break;
//							case 7:
//								System.out.println("enter customer id to remove:");
//								for (Customer customer : admin.getAllCustomers()) {
//									System.out.println(customer.getId() + ":" + (customer.getCustName()));
//								}
//								tempCust = admin.getCustomer(scan.nextInt());
//								if (tempCust != null) {
//									admin.removeCustomer(tempCust);
//								} else
//									System.out.println("no customer with that id!");
//								break;
//							case 8:
//								System.out.println("enter customer id and then info to update: ");
//								for (Customer customer : admin.getAllCustomers()) {
//									System.out.println(customer.getId() + ":" + (customer.getCustName()));
//								}
//								tempCust = admin.getCustomer(scan.nextInt());
//								if (tempCust != null) {
//									System.out.println("please enter new password:");
//									tempCust.setPassword(scan.next());
//									admin.updateCustomer(tempCust);
//								} else
//									System.out.println("no customer with that id!");
//								tempCust = null;
//								break;
//							case 9:
//								System.out.println(admin.getAllCustomers());
//								break;
//							case 10:
//								System.out.println("enter customer id to show info:");
//								for (Customer customer : admin.getAllCustomers()) {
//									System.out.println(customer.getId() + ":" + (customer.getCustName()));
//								}
//								int tempCustomerID = scan.nextInt();
//								System.out.println(admin.getCustomer(tempCustomerID));
//								tempCust = null;
//								break;
//							case 11:
//								System.out.println(admin.getAllCoupons());
//								break;
//							case 12:
//								admin.removeAllCompanies();
//								admin.removeAllCoupons();
//								admin.removeAllCustomers();
//								break;
//							case 13:
//								System.out.println("choose a coupon to remove:");
//								ArrayList<Coupon> allCoupons = admin.getAllCoupons();
//								for (Coupon coupon : allCoupons) {
//									System.out.println(coupon.getId() + ":" + (coupon.getTitle()));
//								}
//								tempCoup = admin.getCouponById(scan.nextLong());
//								if (tempCoup != null) {
//									admin.removeCoupon(tempCoup);
//								} else
//									System.out.println("no coupon with same id");
//								tempCoup = null;
//								break;
//							default:
//								adminEnd = true;
//								break;
//							}
//						}
//					}
//				} else if (menuItem == 2) {
//					System.out.println("enter copmany username and password");
//					String name = scan.next();
//					String pass = scan.next();
//					id =loginManager.login(name, pass, ClientType.Company);
//					if(id!=-1l) {
//						System.out.println("company connected");
//					}
//				//	CompanyService company = (CompanyService) loginManager.login(name, pass, ClientType.Company);
//					if (company != null) {
//						companyEnd = false;
//						while (!companyEnd) {
//							System.out.println();
//							System.out.println(
//									"1.add new coupon\n2.delete coupon by id\n3.update coupon info by id\n4.show company info\n5.show all company coupons\n6.show coupons by:");
//							companyItem = scan.nextInt();
//							switch (companyItem) {
//							case 1:
//								System.out.println("enter new coupon details: ");
//								System.out.println("enter title:");
//								String title = scan.next();
//								System.out.println("enter message:");
//								String message = scan.next();
//								System.out.println("enter amount:");
//								int amount = scan.nextInt();
//								System.out.println("enter price:");
//								double price = scan.nextDouble();
//								String image = "test image";
//								int year = 2019, month = 02, dayOfMonth = 17, year2 = 2019, month2 = 12, dayOfMonth2 = 29;
//								CouponType type = CouponType.HEALTH;
//								Date startDate = Date.valueOf(LocalDate.of(year, month, dayOfMonth));
//								Date endDate = Date.valueOf(LocalDate.of(year2, month2, dayOfMonth2));
//								tempCoup = new Coupon(title, message, image, startDate, endDate, amount, price, type);
//								company.createCoupon(tempCoup,id);
//								tempCoup = null;
//								break;
//							case 2:
//								System.out.println("enter coupon id to remove: ");
//								for (Coupon coupon : company.getAllCoupons(id)) {
//									System.out.println(coupon.getId() + ":" + (coupon.getTitle()));
//								}
//								tempCoup = company.getCoupon(scan.nextLong());
//								if (tempCoup != null) {
//									company.removeCoupon(tempCoup,id);
//								} else
//									System.out.println("no coupon with same id");
//								tempCoup = null;
//								break;
//							case 3:
//								System.out.println("enter coupon id and then info to update: ");
//								for (Coupon coupon : company.getAllCoupons(id)) {
//									System.out.println(coupon.getId() + ":" + (coupon.getTitle()));
//								}
//								tempCoup = company.getCoupon(scan.nextLong());
//								if (tempCoup != null) {
//									System.out.println("enter new end date(year,month,day):");
//									int year3 = scan.nextInt(), month3 = scan.nextInt(), dayOfMonth3 = scan.nextInt();
//									if (year3 >= LocalDate.MIN.getYear() && year3 < LocalDate.MAX.getYear() && 1 <= month3 && month3 <= 12 && dayOfMonth3 > 0 && dayOfMonth3 <= 31) {
//										tempCoup.setEndDate(Date.valueOf(LocalDate.of(year3, month3, dayOfMonth3)));
//									} else
//										System.out.println("incorrect date!!");
//									System.out.println("enter new price:");
//									tempCoup.setPrice(scan.nextDouble());
//									company.updateCoupon(tempCoup);
//								} else
//									System.out.println("no coupon with same id");
//								tempCoup = null;
//								break;
//							case 4:
//								System.out.println(company.getCurrentCompany(id));
//								break;
//							case 5:
//								System.out.println(company.getAllCoupons(id));
//								break;
//							case 6:
//								System.out.println("view coupons by :\n 1.type\n2.upto price\n3.upto end date");
//								int choice = scan.nextInt();
//								if (choice == 1) {
//									ArrayList<CouponType> availableTypes = new ArrayList<CouponType>();
//									for (Coupon coupon : company.getAllCoupons(id)) {
//										if (!availableTypes.contains(CouponType.valueOf(coupon.getType()))) {
//											availableTypes.add(CouponType.valueOf(coupon.getType()));
//										}
//									}
//									for (CouponType couponType : availableTypes) {
//										System.out.println("enter y to see all coupons of type :" + couponType);
//										String answer = scan.next();
//										if (answer.equals("y")) {
//											System.out.println(company.getAllCouponsByType(couponType,id));
//											break;
//										}
//									}
//								} else if (choice == 2) {
//									ArrayList<Double> availablePrices = new ArrayList<Double>();
//									for (Coupon coupon : company.getAllCoupons(id)) {
//										if (!availablePrices.contains(coupon.getPrice())) {
//											availablePrices.add(coupon.getPrice());
//										}
//									}
//									for (Double tempPrice : availablePrices) {
//										System.out.println("enter y to see all coupons upto :" + tempPrice);
//										String answer = scan.next();
//										if (answer.equals("y")) {
//											System.out.println(company.getAllCouponsByPrice(tempPrice,id));
//											break;
//										}
//									}
//								} else if (choice == 3) {
//									ArrayList<Date> availableDates = new ArrayList<Date>();
//									for (Coupon coupon : company.getAllCoupons(id)) {
//										if (!availableDates.contains(coupon.getEndDate())) {
//											availableDates.add(coupon.getEndDate());
//										}
//									}
//									for (Date date : availableDates) {
//										System.out.println("enter y to see all coupons upto :" + date);
//										String answer = scan.next();
//										if (answer.equals("y")) {
//											System.out.println(company.getAllCouponsByDate(date.toLocalDate(),id));
//											break;
//										}
//									}
//								}
//								break;
//							default:
//								companyEnd = true;
//								break;
//							}
//						}
//					}
//				} else if (menuItem == 3) {
//					System.out.println("enter customer username and password:");
//					String name = scan.next(), pass = scan.next();
//					id = loginManager.login(name, pass, ClientType.Customer);
//					if(id != -1l) {
//						System.out.println("connected to customer");
//					}
//				//	CustomerService customer = (CustomerService) 
//					if (customer != null) {
//						customerEnd = false;
//						while (!customerEnd) {
//							System.out.println();
//							System.out.println("1.purchase coupon\n2.show all coupon\n3.show all coupons by:  ");
//							customerItem = scan.nextInt();
//							switch (customerItem) {
//							case 1:
//								System.out.println("enter coupon id to purchase: ");
//								ArrayList<Coupon> availableCoupons = customer.getAvailableCoupons();
//								for (Coupon coupon : availableCoupons) {
//									System.out.println(coupon.getId() + ":" + coupon.getTitle());
//								}
//								int tempCoupId = scan.nextInt();
//								for (Coupon coupon : availableCoupons) {
//									if (coupon.getId() == tempCoupId) {
//										customer.purchaseCoupon(coupon,id);
//										break;
//									}
//								}
//								tempCoup = null;
//								break;
//							case 2:
//								System.out.println(customer.getAllPurchasedCoupons(id));
//								break;
//							case 3:
//								System.out.println("1.show by coupon type\n2.show upto price");
//								int choice = scan.nextInt();
//								if (choice == 1) {
//									ArrayList<CouponType> availableTypes = new ArrayList<CouponType>();
//									for (Coupon coupon : customer.getAllPurchasedCoupons(id)) {
//										if (!availableTypes.contains(CouponType.valueOf(coupon.getType()))) {
//											availableTypes.add(CouponType.valueOf(coupon.getType()));
//										}
//									}
//									for (CouponType type : availableTypes) {
//										System.out.println("enter y to see all coupons of type :" + type);
//										String answer = scan.next();
//										if (answer.equals("y")) {
//											System.out.println(customer.getAllPurchasedCouponsByType(type,id));
//											break;
//										}
//									}
//								} else if (choice == 2) {
//									ArrayList<Double> availablePrices = new ArrayList<Double>();
//									for (Coupon coupon : customer.getAllPurchasedCoupons(id)) {
//										if (!availablePrices.contains(coupon.getPrice())) {
//											availablePrices.add(coupon.getPrice());
//										}
//									}
//									for (Double tempPrice : availablePrices) {
//										System.out.println("enter y to see all coupons upto :" + tempPrice);
//										String answer = scan.next();
//										if (answer.equals("y")) {
//											System.out.println(customer.getAllPurchasedCouponsByPrice(tempPrice,id));
//											break;
//										}
//									}
//								}
//								break;
//							default:
//								customerEnd = true;
//								break;
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e);
//		}
	}
}
