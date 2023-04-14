package org.example;

import org.example.model.Product;
import org.example.service.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/** implements */

@Component
public class DataProvider implements ProductRepository {

    //Map.of is immutable so i got java.lang.UnsupportedOperationException: null when tried to add new product

    private static Map<Product, Integer> products = new HashMap<>();
    {
        products.put(new Product("knife", 50.0) , 1);
        products.put(new Product("forks set 6", 20.50), 2);
    }

    @Override
    public Map<Product, Integer>  getProducts(){
        return products;
    }

    @Override
    public void addProduct(Product product) {
        Integer count = 1;

        if (products.keySet().contains(product))  {
            count = count + products.get(product).intValue();
        }
        products.put(product, count);
    }

   /* public Stream<Map.Entry<Product, Integer>> getValues() {
        return products.entrySet().stream();
    }*/
}
