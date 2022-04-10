package com.example.MouseNest.repository;

import com.example.MouseNest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAll();

    List<Product> findProductsByBrand(String brand);

    List<Product> findAllByName(String name);

    Optional<Product> findProductById(Integer id);

    //List<Product> findProductByName(String name);

}
