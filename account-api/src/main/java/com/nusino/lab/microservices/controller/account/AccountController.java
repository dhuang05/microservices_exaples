package com.nusino.lab.microservices.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nusino.lab.microservices.model.account.Account;
import com.nusino.lab.microservices.model.account.Credit;
import com.nusino.lab.microservices.model.account.Debit;
import com.nusino.lab.microservices.service.account.AccountService;

import io.seata.spring.annotation.GlobalTransactional;


@RestController
@RequestMapping("api")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 10000)
	@PostMapping(path = "credit", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account credit(@RequestBody Credit credit) {
		return accountService.credit(credit);
		
	}
	
	@GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 10000)
	@PostMapping(path = "debit", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account debit(@RequestBody Debit debit) {
		return accountService.debit(debit);
		
	}

	
	@GetMapping(path = "account/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account findAccountById(@PathVariable Long accountId) {
		return accountService.findAccountById(accountId);
		
	}
	
	@GetMapping(path = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Account> findAccounts() {
		return accountService.findAllAccounts();
		
	}
}
