package com.ol.invetoryproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ol.invetoryproject.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>
{
//	@Query("select aname from Alien where aid=?1")
//	String findByAidAndReturnName(int aid);

}
