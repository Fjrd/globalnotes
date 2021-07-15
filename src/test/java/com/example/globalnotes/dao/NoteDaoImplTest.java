package com.example.globalnotes.dao;

import com.example.globalnotes.AppMain;
import com.example.globalnotes.model.Note;
import com.example.globalnotes.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("migrate to spring data")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppMain.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class NoteDaoImplTest {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private NoteDaoImpl noteDao;

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
        User testUser1 = new User("LoginForm.html", "password", "name1", "email@mail.com");
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
        noteDao.deleteInBatch((Iterable)notes);
        assertNotEquals(notes, noteDao.findAllByIds(notes
                .stream()
                .map(Note::getId)
                .collect(Collectors.toList())));
    }

    @Test
    void getOneById() {
        User testUser1 = new User("LoginForm.html", "password", "name1", "email@mail.com");
        userDao.saveAll(Arrays.asList(testUser1));
        Note testNote1 = new Note("name", "body", testUser1);
        noteDao.saveAll(Arrays.asList(testNote1));
        assertNotNull(testNote1.getId());
        assertEquals(testNote1, noteDao.getOneById(testNote1.getId()));
    }
}