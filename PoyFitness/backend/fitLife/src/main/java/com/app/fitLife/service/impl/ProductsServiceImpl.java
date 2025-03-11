package com.app.fitLife.service.impl;

import com.app.fitLife.model.FoodLaunch;
import com.app.fitLife.model.Products;
import com.app.fitLife.repository.ProductsRepositoty;
import com.app.fitLife.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    public ProductsRepositoty productsRepositoty;

    @Override
    public List<Products> getAll() {
        return productsRepositoty.findAll();
    }

    @Override
    public Products getProductById(Long id) {
        return productsRepositoty.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID:" + id));

    }

    @Override
    public Products updateProduct(Long id, Products products) {
        Products productsExist = productsRepositoty.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID:" + id));

        productsExist.setName(products.getName());
        productsExist.setCalories(products.getCalories());
        productsExist.setVegan(products.getVegan());

        return productsRepositoty.save(productsExist);

    }

    @Override
    public void deleteProduct(Long id) {
        productsRepositoty.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID:" + id));

        productsRepositoty.deleteById(id);
    }

    @Override
    public Products addProduct(Products products) {
        return productsRepositoty.save(products);
    }
}
