package com.example.globalnotes.dao;

import com.example.globalnotes.model.Note;
import com.example.globalnotes.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NoteDaoImplTest {

    private EntityManagerFactory factory;
    private EntityManager em;
    private UserDaoImpl userDao;
    private NoteDaoImpl noteDao;

    @BeforeEach
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = factory.createEntityManager();
        userDao = new UserDaoImpl(em);
        noteDao = new NoteDaoImpl(em);
    }

    @AfterEach
    public void tearDown() {
        if (em != null) {
            em.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    void findAll() {
        saveMultipleNotes();
        List<Note> notes = noteDao.findAll();
        assertNotNull(notes);
    }

    @Test
    void findAllByIds() {
        List<Note> notes = saveMultipleNotes();
        noteDao.saveAll(notes);
        List<UUID> noteIdList = notes
                .stream()
                .map(Note::getId)
                .collect(Collectors.toList());
        List<Note> noteFindedById = noteDao.findAllByIds(noteIdList);
        assertTrue(notes.containsAll(noteFindedById));

    }

    @Test
    List<Note> saveMultipleNotes() {
        User testUser1 = new User("login", "password", "name1", "email@mail.com");
        userDao.saveAll(Arrays.asList(testUser1));
        Note testNote1 = new Note("name", "body", testUser1);
        Note testNote2 = new Note("name2", "body", testUser1);
        Note testNote3 = new Note("name3", "body", testUser1);
        List<Note> notes = Arrays.asList(testNote1, testNote2, testNote3);
        noteDao.saveAll(notes);
        for (Note note :
                notes) {
            assertNotNull(note);
            assertNotNull(note.getId());
        }
        return notes;
    }

    @Test
    void deleteInBatch() {
        List<Note> notes = saveMultipleNotes();
        assertNotNull(notes);
        userDao.deleteInBatch((Iterable)notes);
        assertNotEquals(notes, noteDao.findAllByIds(notes
                .stream()
                .map(Note::getId)
                .collect(Collectors.toList())));
    }

    @Test
    void getOneById() {
        User testUser1 = new User("login", "password", "name1", "email@mail.com");
        userDao.saveAll(Arrays.asList(testUser1));
        Note testNote1 = new Note("name", "body", testUser1);
        noteDao.saveAll(Arrays.asList(testNote1));
        assertNotNull(testNote1.getId());
        assertEquals(testNote1, noteDao.getOneById(testNote1.getId()));
    }
}