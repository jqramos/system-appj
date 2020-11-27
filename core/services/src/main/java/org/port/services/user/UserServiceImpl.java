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

    public Iterable<User> getAllReservations(){
        return userDao.findAll();
    }

    public void deleteAllReservations(){
        userDao.deleteAll();
    }

    public void deleteReservationById(String id){
        userDao.deleteById(id);
    }

    public Optional<User> findReservationById(String id){
        return userDao.findById(id);
    }
}
