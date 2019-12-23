package com.CouponSystem.CouponSystem.RestControllers;

import javax.servlet.http.HttpServletRequest;

import com.CouponSystem.CouponSystem.Exceptions.LoginException;

public abstract class ClientController {
	/**
	 * A function to check for login info of a client
	 * 
	 * @param request  The request from the client.
	 * @param name     The name of the client.
	 * @param password The password of the client.
	 * @return True if login was successful.
	 * @throws LoginException Can happen if the login was unsuccessful.
	 */
	public abstract boolean login(HttpServletRequest request, String name, String password) throws LoginException;
}
