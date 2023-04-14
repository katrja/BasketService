package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.assertj.core.util.VisibleForTesting;

import java.util.Objects;
import java.util.UUID;
public class Product {

    @JsonIgnore
    private String id;
    private String name;
    private Double price;

    public Product() {
        this.id = generateUUID();
    }

    public Product(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, Double price) {
        new Product(this.id = generateUUID(), name, price);
    }



    public String getId() {

        return id;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    @VisibleForTesting
    public void setId(String testId) {
        this.id = testId;
    }

     String generateUUID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
