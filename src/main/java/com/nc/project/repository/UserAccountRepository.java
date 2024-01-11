package com.nc.project.repository;

import com.nc.project.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserDetail, Long> {
public UserDetail findByUserId(String UserId);
}
