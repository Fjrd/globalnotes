package com.example.globalnotes.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    List<T> findAll();

    List<T> findAllByIds(Iterable<ID> var1);

    List<T> saveAll(Iterable<T> var1);

    void deleteInBatch(Iterable<T> var1);

    T getOneById(ID id);

}
