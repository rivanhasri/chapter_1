package com.aplikasi.pesanMakanan;

import com.aplikasi.pesanMakanan.entity.Merchant;
import com.aplikasi.pesanMakanan.repository.MerchantRepository;
import com.aplikasi.pesanMakanan.repository.OrderRepository;
import com.aplikasi.pesanMakanan.repository.ProductRepository;
import com.aplikasi.pesanMakanan.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class crud {
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        addMerchantData();
        addOrderData();
        addProductData();
        adduserData();
    }

    public void addMerchantData() {
        Merchant merchant1 = new Merchant();
        merchant1.setMerchantName("Merchant A");
        merchant1.setMerchantLocation("Tangerang");
        merchant1.setOpen(true);
        merchantRepository.save(merchant1);
        Merchant merchant2 = new Merchant();
        merchant2.setMerchantName("Merchant B");
        merchant2.setMerchantLocation("Bandung");
        merchant2.setOpen(true);
        merchantRepository.save(merchant2);
        Merchant merchant3 = new Merchant();
        merchant3.setMerchantName("Merchant C");
        merchant3.setMerchant_location("Jakarta");
        merchant3.setOpen(false);
        merchantRepository.save(merchant3);
    }

    public void addOrderData() {
        Date date = new Date();

        Order order1 = new Order();
        order1.setOrderTime(date);
        order1.setDestinationAddress("Jakarta");
        order1.setCompleted(false);
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setOrderTime(date);
        order2.setDestinationAddress("Serang");
        order2.setCompleted(true);
        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setOrderTime(date);
        order3.setDestinationAddress("Tangsel");
        order3.setCompleted(false);
        orderRepository.save(order3);
    }

    public void addProductData() {
        Product product1 = new Product();
        product1.setProductName("Produk A");
        product1.setPrice(2500);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setProductName("Produk B");
        product2.setPrice(2000);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setProductName("Produk C");
        product3.setPrice(1500);
        productRepository.save(product3);
    }

    public void addUserData(){
        User user1 = new User();
        user1.setUserName("User A");
        user1.setEmailAddress("userA@gmail.com");
        user1.setPassword("usera123");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUserName("User B");
        user2.setEmailAddress("userB@gmail.com");
        user2.setPassword("userb123");
        userRepository.save(user2);

        User user3 = new User();
        user3.setUserName("user C");
        user3.setEmailAddress("userC@gmail.com");
        user3.setPassword("userc123");
        userRepository.save(user3);
    }
}
