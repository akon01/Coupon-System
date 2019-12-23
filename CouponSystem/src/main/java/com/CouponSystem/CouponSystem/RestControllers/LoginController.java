package com.CouponSystem.CouponSystem.RestControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.CouponSystem.Entities.LoginRequest;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;

@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	private AdminController adminController;
	@Autowired
	private CompanyController companyController;
	@Autowired
	private CustomerController customerController;

	/**
	 * A function which receives a login request and checks if the login is valid.
	 * 
	 * @param request      The request from the server.
	 * @param loginRequest An object with name,password and type.
	 * @return True if the login was successful.
	 * @throws LoginException Can happen if no Client of the requested type was
	 *                        found in DB.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public boolean login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) throws LoginException {
		boolean loggedIn = false;
		System.out.println(loginRequest.type);
		switch (loginRequest.type) {
		case "Admin":
			System.out.println("logging in as admin");
			loggedIn = adminController.login(request, loginRequest.name, loginRequest.password);
			break;
		case "Customer":
			System.out.println("logged in as customer");
			loggedIn = customerController.login(request, loginRequest.name, loginRequest.password);
			break;
		case "Company":
			System.out.println("logged in as company");
			loggedIn = companyController.login(request, loginRequest.name, loginRequest.password);
		default:
			break;
		}
		return loggedIn;
	}
}
