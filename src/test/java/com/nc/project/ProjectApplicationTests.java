package com.nc.project;

import com.nc.project.entity.UserAccount;
import com.nc.project.repository.CartRepository;
import com.nc.project.repository.UserAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
    UserAccountRepository userAccountRepository;

    @PersistenceContext
    EntityManager em;

    public UserAccount createUser() {


        return null;
    }


}
