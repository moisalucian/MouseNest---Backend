package com.example.MouseNest.controller;

import com.example.MouseNest.controller.dto.ProductDto;
import com.example.MouseNest.model.Product;
import com.example.MouseNest.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;


    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productServiceImpl.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        try {
            List<Product> productListByBrand = productServiceImpl.getProductsByBrand(brand);
            return new ResponseEntity<>(productListByBrand, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Integer id) {
        try {
            Optional<Product> product = productServiceImpl.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct =  productServiceImpl.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    /*@PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productServiceImpl.updateProduct(product);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedProduct);
    }*/

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto, @PathVariable(value = "id") Integer id) {
        Optional <Product> productFromDatabase = productServiceImpl.getProductById(id);
        productFromDatabase.get().setName(productDto.getName());
        productFromDatabase.get().setBrand(productDto.getBrand());
        productFromDatabase.get().setWeight(productDto.getWeight());
        productFromDatabase.get().setSize(productDto.getSize());
        productFromDatabase.get().setDimensions(productDto.getDimensions());
        productFromDatabase.get().setConnectivity(productDto.getConnectivity());
        productFromDatabase.get().setDescription(productDto.getDescription());
        productFromDatabase.get().setPrice(productDto.getPrice());
        productFromDatabase.get().setUrl(productDto.getUrl());
        productServiceImpl.updateProduct(productFromDatabase.get());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productServiceImpl.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
