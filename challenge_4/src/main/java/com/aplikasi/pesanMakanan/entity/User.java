package com.aplikasi.pesanMakanan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", columnDefinition = "TEXT")
    private String userName;

    @Column(name = "email_address", columnDefinition = "TEXT")
    private String emailAddress;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;
}
