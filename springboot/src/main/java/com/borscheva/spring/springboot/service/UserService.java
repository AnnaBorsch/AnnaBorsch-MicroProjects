package com.borscheva.spring.springboot.service;

import com.borscheva.spring.springboot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void saveUser(User user);

    void updateUser(Long id, User update);

    void deleteUser(Long id);
}
