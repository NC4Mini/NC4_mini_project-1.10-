package com.nc.project.repository;

import com.nc.project.entity.CartItem;
import com.nc.project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
