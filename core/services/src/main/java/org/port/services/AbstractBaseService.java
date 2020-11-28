package org.port.services;

import org.port.dao.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AbstractBaseService<T, I> implements BaseService<T, I> {

    private final BaseRepository<T, I> repo;

    public AbstractBaseService(BaseRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return null;
    }
}
