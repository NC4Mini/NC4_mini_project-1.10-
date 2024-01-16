package com.nc.project.repository;

import com.nc.project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNm(String itemNm);

    // 아이템 테이블 전체조회 하는 쿼리
    @Query(value = "select i from Item i", nativeQuery = true)
    List<Item> findByItemDetail();


}
