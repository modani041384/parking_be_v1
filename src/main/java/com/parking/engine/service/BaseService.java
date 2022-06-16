package com.parking.engine.service;

import java.util.List;

public interface BaseService<T> {
    boolean create(T t);
    boolean createList(List<T> list);
    boolean update(T t);
    boolean updateList(List<T> list);
    T findById(Long id);
    List<T> findAll();
    boolean delete(Long id);
    List<T> search(T filter);
}
