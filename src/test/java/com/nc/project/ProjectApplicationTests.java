package com.nc.project;

import com.nc.project.dto.UserDTO;
import com.nc.project.entity.User;
import com.nc.project.repository.CartRepository;
import com.nc.project.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
class ProjectApplicationTests {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    public User createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUserId("test");
        userDTO.setUserPw("123123");

        return null;
    }


}
