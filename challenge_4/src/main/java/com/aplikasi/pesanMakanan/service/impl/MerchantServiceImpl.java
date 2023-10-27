package com.aplikasi.pesanMakanan.service.impl;

import com.aplikasi.pesanMakanan.entity.Merchant;
import com.aplikasi.pesanMakanan.repository.MerchantRepository;
import com.aplikasi.pesanMakanan.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Merchant addMerchant(Merchant merchant){
        try{
            return merchantRepository.save(merchant);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Gagal menambahkan toko: " + e.getMessage());
        }
    }

    @Override
    public Merchant updateMerchantStatus(String merchantCode, boolean open){
        try {
            merchantRepository.updateMerchantStatus(merchantCode, open);
            return merchantRepository.findById(merchantCode).orElse(null);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Gagal mengupdate status toko: "+e.getMessage());
        }
    }

    @Override
    public List<Merchant> getOpenMerchant(boolean open, Pageable pageable){
        try {
            return merchantRepository.findAllByOpen(open, pageable).getContent();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil toko dengan status open: "+e.getMessage());
        }
    }
}
