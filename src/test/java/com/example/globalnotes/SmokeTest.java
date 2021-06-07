package com.example.globalnotes;


import com.example.globalnotes.model.Note;
import com.example.globalnotes.model.User;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SmokeTest {

    static EntityManagerFactory factory;
    static EntityManager entityManager;
    static User user;
    static Note note;

    @BeforeAll
    public static void setup(){
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = factory.createEntityManager();

        user = new User("login", "password", "name", "email@com");
        note = new Note("name", "body", user);
    }

    @AfterAll
    public static void cleanup(){
        entityManager.close();
        factory.close();
    }


    @Test
    //@Order(1) doesn't work
    public void createUserTest(){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Test
    //@Order(2) doesn't work
    public void createNoteTest() {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.persist(note);
        entityManager.getTransaction().commit();

    }



}
