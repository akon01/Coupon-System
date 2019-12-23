package com.CouponSystem.CouponSystem.Entities;

public class LoginRequest {
	
	public String name;
	public String password;
	public String type;
	@Override
	public String toString() {
		return "LoginRequest [name=" + name + ", password=" + password + ", type=" + type + "]";
	}
	
	
}
