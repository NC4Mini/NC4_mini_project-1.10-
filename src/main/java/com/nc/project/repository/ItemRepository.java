package com.nc.project.repository;

import com.nc.project.entity.Cart;
import com.nc.project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Long> {

}
