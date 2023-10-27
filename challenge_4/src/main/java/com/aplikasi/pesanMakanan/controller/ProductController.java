package com.aplikasi.pesanMakanan.controller;

import com.aplikasi.pesanMakanan.entity.Product;
import com.aplikasi.pesanMakanan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{productCode}")
    public Product updateProduct(@PathVariable String productCode, @RequestBody Product updatedProduct) {
        return productService.updateProduct(productCode, updatedProduct);
    }

    @DeleteMapping("/{productCode}")
    public void deleteProduct(@PathVariable String productCode) {
        productService.deleteProduct(productCode);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }
}
