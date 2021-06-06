package com.example.globalnotes;


import com.example.globalnotes.model.Note;
import com.example.globalnotes.model.User;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SmokeTest {

    User user = new User("login", "password", "name", "email@com");
    Note note = new Note("name", "body", user );

    @Test
    public void createUserAndNoteTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.persist(note);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }



}
