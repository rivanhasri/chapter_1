package com.aplikasi.pesanMakanan.repository;

import com.aplikasi.pesanMakanan.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
