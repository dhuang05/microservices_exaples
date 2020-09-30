package com.nusino.lab.microservices.clientproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nusino.lab.microservices.config.common.AuditInterceptor;
import com.nusino.lab.microservices.model.common.Item;

@FeignClient(name="inventory-service", configuration = AuditInterceptor.class)
@RibbonClient(name="inventory-service", configuration = AuditInterceptor.class)
@RequestMapping("api")
public interface InventoryApiClient {

		@GetMapping(path = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Item> allItems();
		
		@PostMapping(path = "/item/{itemId}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Item increaseItemNumber(@PathVariable(name="itemId") Long itemId, @PathVariable(name="number") Integer number);
		
		@GetMapping(path = "/item/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Item findItemById(@PathVariable(name="itemId") Long itemId);
}
