package com.aplikasi.pesanMakanan.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "product_code", columnDefinition = "TEXT")
    private String product_code;

    @Column(name = "product_name", columnDefinition = "TEXT")
    private String product_name;

    @Column(name = "price")
    private double price;
}
