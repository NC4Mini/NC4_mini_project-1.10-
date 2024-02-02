package com.nc.project.repository;

import com.nc.project.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemName(String itemName);

    // 아이템 테이블 전체조회 하는 쿼리
    @Query(value = "select i from Item i", nativeQuery = true)
    List<Item> findByItemDetail();

    //검색 기능을 위한 메소드
    Page<Item> findByItemNameContaining(String searchKeyword, Pageable pageable);

    @Query(value = "select a" +
            "           from Item a" +
            "           left join a.itemFileList" +
            "           order by rand()")
    Page<Item> findAllOrderByRandom(Pageable pageable);
}
