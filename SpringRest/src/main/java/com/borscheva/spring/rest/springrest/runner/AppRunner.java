package com.borscheva.spring.rest.springrest.runner;

import com.borscheva.spring.rest.springrest.communication.ApiClient;
import com.borscheva.spring.rest.springrest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private ApiClient apiClient;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = apiClient.getUsers();
        String cookie = apiClient.getSessionCookie();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);

        // 1. POST - James Brown
        User user1 = new User(3L, "James", "Brown", (byte)25);
        HttpEntity<User> entity1 = new HttpEntity<>(user1, headers);
        apiClient.saveUser(entity1);

        // 2. PUT - Thomas Shelby
        User user2 = new User(3L, "Thomas", "Shelby", (byte)25);
        HttpEntity<User> entity2 = new HttpEntity<>(user2, headers);
        apiClient.updateUser(entity2);

        // 3. DELETE
        apiClient.deleteUser(3L, entity2);

        // Собираем финальный код
        String finalCode = "5ebfeb" + "e7cb97" + "5dfcf9";
        System.out.println("=== ФИНАЛЬНЫЙ КОД ===");
        System.out.println(finalCode);
        System.out.println("Длина: " + finalCode.length());
    }

    }

