package org.exchange.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {

	public org.exchange.entity.Account createUser(String email, String password);
	
	@Cacheable(value = "accounts", key = "#email")
	public org.exchange.entity.Account readUserByEmail(String email);
	
	public List<org.exchange.entity.Account> readAccounts();
	
	public org.exchange.entity.Account find(Long id);
	
}
