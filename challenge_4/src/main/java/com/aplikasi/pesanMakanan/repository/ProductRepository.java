package com.aplikasi.pesanMakanan.repository;

import com.aplikasi.pesanMakanan.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("UPDATE Product p SET p.open = :open WHERE m.merchant_code = :merchantCode")
    void updateMerchantStatus(@Param("merchantCode") String merchantCode, @Param("open") boolean open);
}
