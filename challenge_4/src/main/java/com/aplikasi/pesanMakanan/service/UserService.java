package com.aplikasi.pesanMakanan.service;

import com.aplikasi.pesanMakanan.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(Long userId, User updatedUser);
    void deleteUser(Long userId);
    List<User> getAllUsers();
}
