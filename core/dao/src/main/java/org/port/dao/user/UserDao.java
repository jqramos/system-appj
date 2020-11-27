package org.port.dao.user;

import org.port.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserDao extends CrudRepository<User, String> {
}
