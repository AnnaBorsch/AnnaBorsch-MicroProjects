package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class MyRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>(userService.getAllRoles());
        return roles;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }


    @PutMapping("/users/{id}")
    public User updateUserRest(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/users/{id}/test")
    public String testUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return "ID: " + user.getId() + ", Name: " + user.getUsername();
    }

}
