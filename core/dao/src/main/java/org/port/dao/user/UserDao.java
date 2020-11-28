package org.port.dao.user;

import org.port.dao.BaseRepository;
import org.port.data.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserDao extends BaseRepository<User, String> {
}
