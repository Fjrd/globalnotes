package com.example.globalnotes.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    List<T> findAll();

    List<T> findAllById(Iterable<ID> var1);

    List<T> saveAll(Iterable<T> var1);

    void flush();

    void deleteInBatch(Iterable<T> var1);

    T getOne(ID var1);

}
