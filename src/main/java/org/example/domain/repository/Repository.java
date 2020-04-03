package org.example.domain.repository;

import java.util.List;

public interface Repository<T, ID extends Integer> {
    T findOneById(ID id);
    List<T> findAll();
    T removeOne(ID id);
    List<T> removeAll();
}
