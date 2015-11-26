package org.exchange.services.impl;

import java.util.List;

import org.exchange.entity.Account;
import org.exchange.repositories.AccountRepository;
import org.exchange.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository user_rep;
	
	@Override
	public org.exchange.entity.Account createUser(String email, String password)
	{
		return user_rep.createUser(email, password);
	}
	
	@Override
	public org.exchange.entity.Account readUser(String email)
	{
		return user_rep.readUserByEmail(email);
	}

	@Override
	public List<Account> readAccounts(String email) {
		return user_rep.readAccounts();
	}
	
	
}
