package com.aplikasi.pesanMakanan.repository;

import com.aplikasi.pesanMakanan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Procedure("delete_user")
    void deleteUser(Long id);

}
