package com.aplikasi.pesanMakanan.service;

import com.aplikasi.pesanMakanan.entity.Merchant;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MerchantService {
    Merchant addMerchant(Merchant merchant);
    Merchant updateMerchantStatus(String merchantCode, boolean open);
    List<Merchant> getOpenMerchant(boolean open, Pageable pageable);

}
