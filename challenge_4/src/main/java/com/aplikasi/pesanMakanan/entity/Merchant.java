package com.aplikasi.pesanMakanan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "Merchant")
@Data
public class Merchant {
    @Id
    @Column(name = "merchant_code", columnDefinition = "TEXT")
    private String merchant_code;

    @Column(name = "merchant_name", columnDefinition = "TEXT")
    private String merchant_name;

    @Column(name = "merchant_location", columnDefinition = "TEXT")
    private String merchant_location;

    @Column(name = "status", columnDefinition = "boolean")
    private boolean open;
}
