package com.aplikasi.pesanMakanan.service;

import com.aplikasi.pesanMakanan.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
}
