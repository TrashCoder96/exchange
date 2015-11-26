package org.exchange.services;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {

	public org.exchange.entity.Account createUser(String email, String password);
	
	public org.exchange.entity.Account readUser(String email);
	
	public List<org.exchange.entity.Account> readAccounts(String email);
	
}
