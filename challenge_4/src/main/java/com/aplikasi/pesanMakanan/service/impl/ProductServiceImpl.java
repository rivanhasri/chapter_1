package com.aplikasi.pesanMakanan.service.impl;

import com.aplikasi.pesanMakanan.entity.Product;
import com.aplikasi.pesanMakanan.repository.ProductRepository;
import com.aplikasi.pesanMakanan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Gagal menambahkan produk: " + e.getMessage());
        }
    }

    @Override
    public Product updateProduct(String productCode, Product updatedProduct){
        try {
            Product existingProduct = productRepository.findById(productCode).orElse(null);
            if (existingProduct != null) {
                existingProduct.setProductName(updatedProduct.getProductName());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setMerchantCode(updatedProduct.getMerchantCode());
                return productRepository.save(existingProduct);
            } else {
                throw new RuntimeException("Produk tidak ditemukan: " + productCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengupdate produk: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(String productCode){
        try{
            productRepository.deleteById(productCode);
        } catch (Exception e){
            throw new RuntimeException("Gagal menghapus produk: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProduct(){
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil semua produk: " + e.getMessage());
        }
    }
}
