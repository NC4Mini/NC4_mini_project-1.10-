package com.nc.project.repository;

import com.nc.project.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <Cart, Long> {

    // 장바구니 확인시 유저 정보를 기반으로 장바구니를 찾는 메소드
    Cart findByUserAccountId(Long id);

}
