package com.nusino.lab.microservices.service.account;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nusino.lab.microservices.model.account.Account;
import com.nusino.lab.microservices.model.account.Credit;
import com.nusino.lab.microservices.model.account.Debit;
import com.nusino.lab.microservices.model.common.OperationType;
import com.nusino.lab.microservices.repository.account.AccountRepository;
import com.nusino.lab.microservices.streams.common.AuditLogProducer;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AuditLogProducer auditLogProducer;
	
	@Transactional
	public Account credit(Credit credit) {
		if(credit.getAmount() == null || credit.getAmount().doubleValue() <= 0) {
			throw new RuntimeException("Negative credit amount!");
		}

		accountRepository.updateAccountById(credit.getAccountId(), credit.getAmount());
		Account account = accountRepository.findAccountById(credit.getAccountId());
		
		if(account == null) {
			throw new RuntimeException("Account not found :" +  credit.getAccountId());
		}
		
		return account;
	}
	
	
	public List<Account> findAllAccounts() {
		List<Account> accounts = accountRepository.findAllAccounts();
		return accounts;
	}
	
	@Transactional
	public Account debit(Debit debit) {
		Account account = accountRepository.findAccountById(debit.getAccountId());
		if(account == null) {
			auditLogProducer.auditError(Account.class, null, debit, "Debit Account not found");
			throw new RuntimeException("Account not found :" +  debit.getAccountId());
		}
		if(debit.getAmount() == null || debit.getAmount().doubleValue() <= 0 || (account.getBalance().doubleValue() - debit.getAmount().doubleValue()) < 500) {
			auditLogProducer.auditError(Account.class, account.getAccountId(), debit, "Negative debit amount or Not enough amount to debit, should have $500 in account! " );
			throw new RuntimeException("Negative debit amount or Not enough amount to debit!");
		}
		BigDecimal amount = new BigDecimal(-1 * debit.getAmount().doubleValue());
		accountRepository.updateAccountById(debit.getAccountId(), amount);
		auditLogProducer.auditLog(OperationType.UPDATE, Account.class, account.getAccountId(), account, "then, " + debit.getAccountId() + "add amount " + amount);
		return accountRepository.findAccountById(debit.getAccountId());
		
	}
	
	public Account findAccountById(Long accountId) {
		Account account =  accountRepository.findAccountById(accountId);
		if(account == null) {
			auditLogProducer.auditError(Account.class, accountId, null, "Debit Account not found");
			throw new RuntimeException("Account not found :" +  accountId);
		}
		
		return account;
	}
}
