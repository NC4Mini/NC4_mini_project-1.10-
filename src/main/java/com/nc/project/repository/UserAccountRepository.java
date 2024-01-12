package com.nc.project.repository;

import com.nc.project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    public UserAccount findByUserId(String UserId);
}
