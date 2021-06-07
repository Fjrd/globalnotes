package com.example.globalnotes.repository;

import com.example.globalnotes.model.User;

import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements GenericRepository<User, UUID>{

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<UUID> var1) {
        return null;
    }

    @Override
    public List<User> saveAll(Iterable<User> var1) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public void deleteInBatch(Iterable<User> var1) {

    }

    @Override
    public User getOne(UUID var1) {
        return null;
    }
}
