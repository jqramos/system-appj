package org.port.services.user;

import org.port.dao.user.UserDao;
import org.port.data.model.User;
import org.port.services.AbstractBaseService;
import org.port.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserServiceImpl extends AbstractBaseService<User, String> implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    public User save(User user){
        return userDao.save(user);
    }

    public Iterable<User> getAllUser(){
        return userDao.findAll();
    }

    public void deleteUserById(String id){
        userDao.deleteById(id);
    }

    public Optional<User> findUserById(String id){
        return userDao.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
