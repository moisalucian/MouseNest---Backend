package com.example.MouseNest.service;

import com.example.MouseNest.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByBrand(String brand);

    Optional<Product> getProductById(Integer id);

    Product updateProduct(Product product);

    Product addProduct(Product product);

    void deleteProduct(Integer id);

}
