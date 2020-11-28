package org.port.services;

import org.port.data.search.SearchData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T, I> {

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    Page<T> search(SearchData<T> searchData);
}
