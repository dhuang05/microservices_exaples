package com.nusino.lab.microservices.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nusino.lab.microservices.model.inventory.Item;
import com.nusino.lab.microservices.service.inventory.InventoryService;

import io.seata.spring.annotation.GlobalTransactional;


@RestController
@RequestMapping("/api")
public class InventoryController {

	@Autowired
	private InventoryService orderService;

	@GetMapping(path = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Item> allItems() {
		return orderService.allItems();
	}
	
	@GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 10000)
	@PostMapping(path = "/item/{itemId}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Item increaseItemNumber(@PathVariable Long itemId, @PathVariable Integer number) {
		return orderService.increaseItemQuantity(itemId, number);
	}
	
	
	@GetMapping(path = "/item/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Item findItemById(Long itemId) {
		return orderService.findItemById(itemId);
	}
	
}
