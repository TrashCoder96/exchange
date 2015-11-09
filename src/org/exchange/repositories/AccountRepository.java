package org.exchange.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {

	public org.exchange.entity.Account createUser(String email, String password);
	
	public org.exchange.entity.Account readUserByEmail(String email);
	
	public List<org.exchange.entity.Account> readAccounts(Long user_id, String str, Integer count, Integer position);
}
