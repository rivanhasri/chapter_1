package com.aplikasi.pesanMakanan.service.impl;

import com.aplikasi.pesanMakanan.entity.Order;
import com.aplikasi.pesanMakanan.repository.OrderRepository;
import com.aplikasi.pesanMakanan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Gagal membuat pesanan: " + e.getMessage());
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil semua pesanan: " + e.getMessage());
        }
    }
}
