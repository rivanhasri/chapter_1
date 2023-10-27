package com.aplikasi.pesanMakanan.controller;

import com.aplikasi.pesanMakanan.entity.Merchant;
import com.aplikasi.pesanMakanan.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public Merchant addMerchant(@RequestBody Merchant merchant){
        return merchantService.addMerchant(merchant);
    }

    @PutMapping("/{merchantCode}/status")
    public Merchant updateMerchantStatus(@PathVariable String merchantCode, @RequestParam boolean open) {
        return merchantService.updateMerchantStatus(merchantCode, open);
    }

    @GetMapping("/open")
    public List<Merchant> getOpenMerchant(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size);
        return merchantService.getOpenMerchant(true, pageable);
    }
}
