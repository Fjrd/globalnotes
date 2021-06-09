package com.example.globalnotes.dao;

import com.example.globalnotes.model.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final EntityManager manager;

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

        manager.getTransaction().begin();
        try {
            for(Iterator<User> iterator = var1.iterator(); iterator.hasNext();){
                manager.persist(iterator.next());
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        return (List<User>) var1;
    }

    @Override
    public void deleteInBatch(Iterable<User> var1) {

    }

    @Override
    public User getOne(UUID var1) {
        return null;
    }
}
