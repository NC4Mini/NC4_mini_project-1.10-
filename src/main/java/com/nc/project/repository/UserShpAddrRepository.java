package com.nc.project.repository;

import com.nc.project.entity.UserShpAddr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserShpAddrRepository extends JpaRepository <UserShpAddr, Long> {
    Optional<UserShpAddr> findByUserAccount_IdAndAddrStandardEquals(long id, char addrStandard);
}
