package com.app.fitLife.repository;

import com.app.fitLife.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepositoty extends JpaRepository<Products, Long> {
}
