package com.example.globalnotes.dao;

import com.example.globalnotes.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public List<User> findAllByIds(Iterable<UUID> var1) {
        List<User> userList = new ArrayList<>();
        for (UUID id :
                var1) {
            userList.add(em.find(User.class, id));
        }
        return userList;
    }

    @Override
    public List<User> saveAll(Iterable<User> var1) {
        em.getTransaction().begin();
        try {
            for (User user : var1) {
                em.persist(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        return (List<User>) var1;
    }

    @Override
    public void deleteInBatch(Iterable<User> var1) {
        em.getTransaction().begin();
        try {
            for (User user : var1) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public User getOneById(UUID id) {
       return em.find(User.class, id);
    }
}
