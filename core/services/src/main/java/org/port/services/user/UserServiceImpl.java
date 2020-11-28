package org.port.services.user;

import org.port.dao.user.UserDao;
import org.port.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
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
}
