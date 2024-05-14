package com.poc2.TaskExecutor.repository;

import com.poc2.TaskExecutor.entity.Product;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
