package com.example.demo.service;

public interface UserService {
    String login(String username, String password);
    String logout(String username);
}
