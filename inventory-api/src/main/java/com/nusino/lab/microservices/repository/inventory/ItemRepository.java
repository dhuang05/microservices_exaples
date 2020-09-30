package com.nusino.lab.microservices.repository.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nusino.lab.microservices.model.inventory.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query(value = "SELECT * from MS_ITEM", nativeQuery = true)
	public List<Item> findAllItems();
	
	@Query(value = "SELECT * from MS_ITEM where item_id = :itemId", nativeQuery = true)
	public Item findItemById(@Param("itemId")  Long itemId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE MS_ITEM set quantity = (quantity + :number) where item_id = :itemId", nativeQuery = true)
	public void updateItemById(@Param("itemId")  Long itemId, @Param("number")  Integer number);


}
