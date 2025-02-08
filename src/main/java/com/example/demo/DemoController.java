package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apii")
public class  DemoController {

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id) {
        return "User ID: " + id;
    }

    @PostMapping("/users")
    public String createUser() {
        return "User created!";
    }

    @PostMapping("/admin/create")
    public String createAdmin() {
        return "Admin created!";
    }

    @GetMapping("/products")
    public String getProducts() {
        return "List of products";
    }
}