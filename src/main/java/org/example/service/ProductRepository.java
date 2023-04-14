package org.example.service;

import org.example.DataProvider;
import org.example.model.Product;

import java.util.Map;

public interface ProductRepository {


     Map<Product, Integer> getProducts();

    void addProduct(Product product);
}
