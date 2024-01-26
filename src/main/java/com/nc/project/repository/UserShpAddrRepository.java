package com.nc.project.repository;

import com.nc.project.entity.UserShpAddr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserShpAddrRepository extends JpaRepository <UserShpAddr, Long> {
    UserShpAddr findByUserAccount_Id(Long id);

    UserShpAddr findByUserAccount_IdAndAddrStandard (long userAccount_id, char addrStandard);

    // id와 addrId로 배송지를 찾는 기능
    UserShpAddr findByUserAccount_IdAndAddrId (long userAccount_id, int addrId);

    // id로 배송지 목록을 찾는 기능
    List<UserShpAddr> findAllByUserAccount_Id(long id);

}
