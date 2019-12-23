package com.CouponSystem.CouponSystem.Services;

import com.CouponSystem.CouponSystem.Entities.ClientType;
import com.CouponSystem.CouponSystem.Exceptions.LoginException;

public interface CouponClientService {
	
	/**
	 * Allows login to different client types.
	 * @param name Inputed name.
	 * @param password Inputed password.
	 * @return Id of the connected client.
	 * @throws LoginException Can happen when logging in has failed.
	 */
	public long login(String name,String password) throws LoginException;
	
	public ClientType type=null;
}
