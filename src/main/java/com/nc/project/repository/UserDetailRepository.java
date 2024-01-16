package com.nc.project.repository;

import com.nc.project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUserId (String userId);
}
