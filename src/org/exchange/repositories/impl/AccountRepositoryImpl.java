package org.exchange.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.exchange.entity.Account;
import org.exchange.repositories.AccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public org.exchange.entity.Account createUser(String email, String password) {
		org.exchange.entity.Account user = new org.exchange.entity.Account();
		user.setEmail(email);
		user.setPassword(DigestUtils.sha256Hex(password));
		manager.merge(user);
		return user;
	}

	@Override
	@Transactional
	public List<Account> readAccounts() {
		List<org.exchange.entity.Account> accounts = manager.createQuery("SELECT account FROM Account account", org.exchange.entity.Account.class).getResultList();
	    return accounts;
	}

	@Override
	@Transactional
	public Account readUserByEmail(String email) {
		List<org.exchange.entity.Account> accounts = manager.createQuery("SELECT account FROM Account account WHERE account.email = :email", org.exchange.entity.Account.class).setParameter("email", email).getResultList();
		if (accounts.size() > 0)
		return accounts.get(0);
		else
		return null;
	}

	@Override
	@Transactional
	public Account find(Long id) {
		return manager.find(Account.class, id);
	}
	
	

}
