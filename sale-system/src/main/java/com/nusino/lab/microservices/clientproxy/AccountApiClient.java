package com.nusino.lab.microservices.clientproxy;



import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nusino.lab.microservices.config.common.AuditInterceptor;
import com.nusino.lab.microservices.model.common.Account;
import com.nusino.lab.microservices.model.common.Credit;
import com.nusino.lab.microservices.model.common.Debit;


@FeignClient(name="account-service", configuration = AuditInterceptor.class)
@RibbonClient(name="account-service", configuration = AuditInterceptor.class)
@RequestMapping("api")
public interface AccountApiClient {
	@PostMapping(path = "credit", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account credit(@RequestBody Credit credit);
	
	@PostMapping(path = "debit", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account debit(@RequestBody Debit debit);

	
	@GetMapping(path = "account/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Account findAccountById(@PathVariable(name="accountId") Long accountId);
	
	@GetMapping(path = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Account> findAccounts();
}
