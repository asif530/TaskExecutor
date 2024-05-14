package com.poc2.TaskExecutor.service;

import com.poc2.TaskExecutor.entity.Product;
import com.poc2.TaskExecutor.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();
        System.out.println("Product is called!!!");
        products = productRepository.findAll();
        return products;
    }

    public void getAllProductVoid(){
        List<Product> products = new ArrayList<>();
        products = productRepository.findAll();
    }
}
