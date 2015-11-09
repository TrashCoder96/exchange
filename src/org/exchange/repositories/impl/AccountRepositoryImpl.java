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
	public org.exchange.entity.Account readUserByEmail(String email) {
		List<Account> accounts = manager.createQuery("SELECT user FROM Account user WHERE user.email = :email", org.exchange.entity.Account.class).setParameter("email", email).getResultList();
		if (accounts.size() > 0)
		return accounts.get(0);
		else
		return null;
	}

	@Override
	@Transactional
	public List<org.exchange.entity.Account> readAccounts(Long user_id, String str, Integer count, Integer position) {
		String pattern = "%" + str;
		return manager.createQuery("SELECT user FROM Account user WHERE user.email LIKE :pattern", org.exchange.entity.Account.class).setParameter("pattern", pattern).getResultList();
	}
	
	

}
