package com.nusino.lab.microservices.repository.account;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nusino.lab.microservices.model.account.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE MS_ACCOUNT SET balance = (balance + :amount) WHERE account_id = :accountId", nativeQuery = true)
	public void updateAccountById(@Param("accountId") Long accountId, @Param("amount") BigDecimal amount);
	
	@Query(value = "SELECT * from MS_ACCOUNT WHERE account_id = :accountId", nativeQuery = true)
	public Account findAccountById(@Param("accountId") Long accountId);

	@Query(value = "SELECT * from MS_ACCOUNT", nativeQuery = true)
	public List<Account> findAllAccounts();
}
