package com.aplikasi.pesanMakanan.service;

import com.aplikasi.pesanMakanan.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(String productCode, Product updatedProduct);
    void deleteProduct(String productCode);
    List<Product> getAllProduct();

}
