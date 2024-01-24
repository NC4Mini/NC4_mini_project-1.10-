package com.nc.project.repository;

import com.nc.project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUserId(String UserId);

    int countByUserId(String userId);

    int countByUserEmail(String userEmail);

    Optional<UserAccount> findByUserIdAndUserPw(String userId, String userPw);
}
