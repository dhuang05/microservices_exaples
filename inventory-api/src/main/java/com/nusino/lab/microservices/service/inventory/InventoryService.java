package com.nusino.lab.microservices.service.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nusino.lab.microservices.model.common.OperationType;
import com.nusino.lab.microservices.model.inventory.Item;
import com.nusino.lab.microservices.repository.inventory.ItemRepository;
import com.nusino.lab.microservices.streams.common.AuditLogProducer;

@Service
public class InventoryService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private AuditLogProducer auditLogProducer;

	public List<Item> allItems() {
		return itemRepository.findAllItems();
	}

	@Transactional
	public Item increaseItemQuantity(Long itemId, Integer number) {
		Item item = itemRepository.findItemById(itemId);
		if (item == null) {
			auditLogProducer.auditError(Item.class,  itemId, ("itemId " + itemId + " add quality " + number), "Item notfound");
			throw new RuntimeException("Not item found " + itemId + " add quality " + number);
		}
		if (number != null && number != 0) {
			if (number < 0) {
				if ((item.getQuantity() + number) < 0) {
					auditLogProducer.auditError(Item.class,  itemId, ("itemId " + itemId + " add quality " + number), "Not enough item quantity available");
					throw new RuntimeException("Not enough item quantity");
				}
			}
		}
		itemRepository.updateItemById(itemId, number);
		auditLogProducer.auditLog(OperationType.UPDATE,  Item.class,  itemId, "itemId " + itemId + " add quality " + number,  "Update item quantity");
		return itemRepository.findItemById(itemId);
	}

	public Item findItemById(Long itemId) {
		return itemRepository.findItemById(itemId);
	}

	@Transactional
	public void updateItemById(Long itemId, Integer number) {
		itemRepository.updateItemById(itemId, number);
	}
}
