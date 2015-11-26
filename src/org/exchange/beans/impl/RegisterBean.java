package org.exchange.beans.impl;

import javax.faces.bean.ManagedBean;
import org.exchange.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope(value = "session")
public class RegisterBean {

	@Autowired
	private AccountService acc_service;
	
	private String email;
	
	private String password;
	
	public void registerAccount()
	{
		acc_service.createUser(email, password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
