package com.example.globalnotes.repository;

import com.example.globalnotes.model.Note;

import java.util.List;
import java.util.UUID;

public class NoteRepositoryImpl implements GenericRepository <Note, UUID>{
    @Override
    public List<Note> findAll() {
        return null;
    }

    @Override
    public List<Note> findAllById(Iterable<UUID> var1) {
        return null;
    }

    @Override
    public List<Note> saveAll(Iterable<Note> var1) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<Note> var1) {

    }

    @Override
    public Note getOne(UUID var1) {
        return null;
    }
}
