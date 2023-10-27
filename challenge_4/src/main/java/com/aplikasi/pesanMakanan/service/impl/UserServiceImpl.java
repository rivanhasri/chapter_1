package com.aplikasi.pesanMakanan.service.impl;

import com.aplikasi.pesanMakanan.entity.User;
import com.aplikasi.pesanMakanan.repository.UserRepository;
import com.aplikasi.pesanMakanan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Gagal menambahkan user: " + e.getMessage());
        }
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        try {
            User existingUser = userRepository.findById(userId).orElse(null);
            if (existingUser != null) {
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setEmailAddress(updatedUser.getEmailAddress());
                existingUser.setPassword(updatedUser.getPassword());
                return userRepository.save(existingUser);
            } else {
                throw new RuntimeException("User tidak ditemukan: " + userId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengupdate user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghapus user: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil semua user: " + e.getMessage());
        }
    }
}
