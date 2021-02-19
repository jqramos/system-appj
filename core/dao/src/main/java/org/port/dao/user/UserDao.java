package org.port.dao.user;

import org.port.dao.BaseRepository;
import org.port.data.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserDao extends BaseRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
