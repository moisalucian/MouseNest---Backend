package com.example.MouseNest.service;

import com.example.MouseNest.model.Product;
import com.example.MouseNest.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findProductsByBrand(brand);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        Optional<Product> product = productRepository.findProductById(id);
        if (!product.isPresent()) {
            throw new IllegalArgumentException("Product not found!");
        }
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> searchedProductById = productRepository.findProductById(product.getId());
        if (!searchedProductById.isPresent()) {
            throw new IllegalArgumentException("Product was not found");
        }
        return productRepository.save(product);
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductById(product.getId());
        if (productOptional.isPresent()){
            throw new IllegalArgumentException("Product already exists!");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findProductById(id);
        if(!productOptional.isPresent()){
            throw new IllegalArgumentException("Product doesn't exist!");
        }
        productRepository.deleteById(id);
    }
}
