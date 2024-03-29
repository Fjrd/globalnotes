package com.example.globalnotes.dao;

import com.example.globalnotes.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Repository
public class NoteDaoImpl implements NoteDao{

    private final EntityManager em;

    @Override
    public List<Note> findAll() {
        return em.createQuery("select n from Note n", Note.class)
                .getResultList();
    }

    @Override
    public List<Note> findAllByIds(Iterable<UUID> var1) {
        List<Note> notes = new ArrayList<>();
        for (UUID id :
                var1) {
            notes.add(em.find(Note.class, id));
        }
        return notes;
    }

    @Override
    public List<Note> saveAll(Iterable<Note> var1) {
        em.getTransaction().begin();
        try {
            for (Note note : var1) {
                em.persist(note);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        return (List<Note>) var1;
    }

    @Override
    public void deleteInBatch(Iterable<Note> var1) {
        em.getTransaction().begin();
        try {
            for (Note note : var1) {
                em.remove(note);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Note getOneById(UUID id) {
        return em.find(Note.class, id);
    }
}
