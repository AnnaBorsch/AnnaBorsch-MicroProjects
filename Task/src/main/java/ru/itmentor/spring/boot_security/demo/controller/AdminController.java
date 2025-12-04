package ru.itmentor.spring.boot_security.demo.controller;

import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }


    @GetMapping("/users/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user-form";
    }

    @PostMapping("/users")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam(value = "roles", required = false) Set<String> roles) {
        userService.createUser(username, password, email, roles);
        return "redirect:/admin/users";
    }


    @GetMapping("/users/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user-form";
    }

    @PostMapping("/users/{id}")
    public String updateUserForm(@PathVariable Long id,
                                 @RequestParam String username,
                                 @RequestParam String email,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(value = "roles", required = false)
                                 Set<String> roles) {
        userService.updateUserFromForm(id, username, email, password, roles);
        return "redirect:/admin/users";
    }
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}