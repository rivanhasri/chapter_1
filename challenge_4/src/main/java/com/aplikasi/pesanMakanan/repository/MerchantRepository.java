package com.aplikasi.pesanMakanan.repository;

import com.aplikasi.pesanMakanan.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    Page<Merchant> findAllByOpen(boolean open, Pageable pageable);

    Page<Merchant> findAll(Pageable pageable);

    void deleteByMerchantCode(String merchantCode);

    @Query("UPDATE Merchant m SET m.open = :open WHERE m.merchant_code = :merchantCode")
    void updateMerchantStatus(@Param("merchantCode") String merchantCode, @Param("open") boolean open);
}
