package com.example.globalnotes;


import com.example.globalnotes.model.User;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SmokeTest {
    @Test
    public void createUserTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();

        User user = new User("login", "password", "name", "email@com");
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
