package org.port.services.user;

import org.port.data.model.Art;
import org.port.data.model.User;
import org.port.services.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends BaseService<User, String>, UserDetailsService {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);
}
