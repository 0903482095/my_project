package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
	User findByEmail(String email);

    void updatePassword(String password, Long userId);
}
