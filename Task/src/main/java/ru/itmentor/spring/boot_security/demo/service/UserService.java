package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User save(User user) {
        // Если пароль новый - шифруем его
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User updateUserFromForm(Long id, String username, String email,
                                   String password, Set<String> roleNames) {
        User user = findById(id);
        user.setUsername(username);
        user.setEmail(email);

        // Пароль обновляем только если передан
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        // Обновляем роли
        if (roleNames != null) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : roleNames) {
                Role role = roleRepository.findByName(roleName)
                        .orElseGet(() -> roleRepository.save(new Role(roleName)));
                roles.add(role);
            }
            user.setRoles(roles);
        }

        return save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Создать пользователя с ролями
    public User createUser(String username, String password, String email, Set<String> roleNames) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                    .orElseGet(() -> {
                        Role newRole = new Role(roleName);
                        return roleRepository.save(newRole);
                    });
            roles.add(role);
        }
        user.setRoles(roles);

        return this.save(user);
    }


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}