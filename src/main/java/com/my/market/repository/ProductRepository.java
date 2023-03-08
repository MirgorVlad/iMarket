package com.my.market.repository;

import com.my.market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByTitle(String title);
    void deleteById(int id);
}
