package org.example.model;

import org.assertj.core.util.VisibleForTesting;
import org.example.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
@Component
public class Basket {
    //TODO:
    //concurency map
    //adding by id
    //separate repositories for product and basket
    //

    //private <Integer, Map<Product,Number> products = new HashMap<Integer, Map<Product,Number>();
  //  private final DataProvider dataProvider;

    private Map<Product, Integer> products = new HashMap<>();
            //DataProvider.getProducts();

    public Basket() {}

    public Map<Product, Integer>  getAllProductsWithCount(){ return products; }

    /*public Set<Product> getAllProducts(){
       return products.keySet();
    }*/

    public Product getProductById(String id){
        for(Product product : products.keySet()){
            if (product.getId().equals(id)) return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void add(Product product){
        Integer count = 1;

        if (products.keySet().contains(product))  {
            count = count + products.get(product).intValue();
        }
        products.put(product, count);
    }

    public void remove(String id) {
        Product product = null;
        for (Product p : products.keySet()) {
            if (p.getId().equals(id)) {
                product = p;
            }
        }

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Integer productCount = products.get(product);
        if (productCount > 1 ) {
            products.put(product, productCount - 1);
        } else {
            products.remove(product);
        }
    }

    public Double getTotalPrice() {
        Double total = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            //TODO when proce is zero?
            total += product.getPrice() * entry.getValue();
        }
        return total;
    }

    @VisibleForTesting
    public void setProducts(Map<Product, Integer> testProducts) {
        this.products = testProducts;
    }
}
