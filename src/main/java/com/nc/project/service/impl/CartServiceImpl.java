package com.nc.project.service.impl;//package com.example.tempproject.service.impl;

import com.nc.project.dto.ItemDTO;
import com.nc.project.repository.CartRepository;
import com.nc.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    // 장바구니 리스트에 물건 담기
//    @Override
//    public void addCart(ItemDTO itemDTO) {
//
//
//
//    }

}