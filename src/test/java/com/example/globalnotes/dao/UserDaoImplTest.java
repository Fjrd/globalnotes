package com.example.globalnotes.dao;

import com.example.globalnotes.AppMain;
import com.example.globalnotes.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("migrate to spring data")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppMain.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserDaoImplTest {

    @Autowired
    private UserDaoImpl userDao;

    @Test
    void findAll() {
        saveMultipleUser();
        List<User> users = userDao.findAll();
        assertNotNull(users);

    }

    @Test
    void findAllById() {
        User testUser1 = new User("LoginForm.html", "password", "name1", "email@mail.com");
        User testUser2 = new User("asdas", "pass", "name3", "email2@mail.com");
        User testUser3 = new User("gfdhfgd", "word", "n123", "asdsa@mail.com");
        User testUser4 = new User("234gf", "123", "nfgfgg", "e23423@mail.com");
        List<User> users = Arrays.asList(testUser1, testUser2, testUser3, testUser4);
        userDao.saveAll(users);
        List<UUID> userIdList = users
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());
        List<User> usersFindedById = userDao.findAllByIds(userIdList);
        assertTrue(users.containsAll(usersFindedById));
    }

    @Test
    void saveOneUser() {
        User testUser = new User("LoginForm.html", "password", "name", "email@mail.com");
        userDao.saveAll(Collections.singletonList(testUser));
        assertNotNull(testUser);
        assertNotNull(testUser.getId());
    }
    
    @Test
    void saveMultipleUser() {
        User testUser1 = new User("LoginForm.html", "password", "name1", "email@mail.com");
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
        User testUser1 = new User("LoginForm.html", "password", "name1", "email@mail.com");
        userDao.saveAll(Arrays.asList(testUser1));
        assertEquals(testUser1, userDao.getOneById(testUser1.getId()));
        userDao.deleteInBatch(Arrays.asList(testUser1));
        assertNull(userDao.getOneById(testUser1.getId()));
    }

    @Test
    void getOneById() {
        User testUser1 = new User("LoginForm.html", "password", "name1", "email@mail.com");
        userDao.saveAll(Arrays.asList(testUser1));
        assertNotNull(testUser1.getId());
        assertEquals(testUser1, userDao.getOneById(testUser1.getId()));
    }
}