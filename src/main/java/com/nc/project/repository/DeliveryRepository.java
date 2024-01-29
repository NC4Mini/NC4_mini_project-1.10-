package com.nc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nc.project.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

    
}
