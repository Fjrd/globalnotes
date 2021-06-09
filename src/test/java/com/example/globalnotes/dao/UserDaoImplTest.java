package com.example.globalnotes.dao;

import com.example.globalnotes.model.User;
import org.hibernate.hql.spi.id.cte.CteValuesListBulkIdStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserDaoImplTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDaoImpl userDao;

    @BeforeEach
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        userDao = new UserDaoImpl(manager);
    }

    @AfterEach
    public void tearDown() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllById() {
    }

    @Test
    void saveOneUser() {
        User testUser = new User("login", "password", "name", "email@mail.com");
        userDao.saveAll(Arrays.asList(testUser));
        assertNotNull(testUser);
        assertNotNull(testUser.getId());

    }
    
    @Test
    void saveMultipleUser() {
        User testUser1 = new User("login", "password", "name1", "email@mail.com");
        User testUser2 = new User("asdas", "pass", "name3", "email2@mail.com");
        User testUser3 = new User("gfdhfgd", "word", "n123", "asdsa@mail.com");
        User testUser4 = new User("234gf", "123", "nfgfgg", "e23423@mail.com");
        List<User> userList = Arrays.asList(testUser1, testUser2, testUser3, testUser4);
        userDao.saveAll(userList);
        for (User user :
                userList) {
            assertNotNull(user);
            assertNotNull(user.getId());
        }


    }

    @Test
    void deleteInBatch() {
    }

    @Test
    void getOne() {
    }
}