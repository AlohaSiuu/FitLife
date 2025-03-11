package com.app.fitLife.controller;

import com.app.fitLife.model.Products;
import com.app.fitLife.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@PreAuthorize("isAuthenticated()")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<Products>> getAll(){
       return ResponseEntity.ok(productsService.getAll());
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Products> getProductById(@PathVariable Long id){
        Products products = productsService.getProductById(id);
        if (products == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(products);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products products){
        Products productsExist = productsService.getProductById(id);
        if (productsExist == null){
            return ResponseEntity.notFound().build();
        }

        productsExist.setName(products.getName());
        productsExist.setCalories(products.getCalories());
        productsExist.setVegan(products.getVegan());

        return ResponseEntity.ok(productsService.updateProduct(id, productsExist));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Products> addProduct(@RequestBody Products products){
        return ResponseEntity.ok(productsService.addProduct(products));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        Products products = productsService.getProductById(id);
        if (products == null){
            return ResponseEntity.notFound().build();
        }
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
