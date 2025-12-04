package com.borscheva.spring.springboot.service;

import com.borscheva.spring.springboot.dao.UserDao;
import com.borscheva.spring.springboot.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    @Transactional
    public void updateUser(Long id, User update) {
        userDao.updateUser(id, update);

    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);

    }
}
