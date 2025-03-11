package com.app.fitLife.service;

import com.app.fitLife.model.Products;

import java.util.List;

public interface ProductsService {

    List<Products> getAll();
    Products getProductById(Long id);
    Products updateProduct(Long id, Products products);
    void deleteProduct(Long id);
    Products addProduct(Products products);
}
