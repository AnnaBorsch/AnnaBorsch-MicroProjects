package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index"; // index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }
}
