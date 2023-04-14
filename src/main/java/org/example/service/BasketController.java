package org.example.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.model.Basket;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.*;
import java.util.Map;

@OpenAPIDefinition(
        info = @Info(
                title = "Basket Service API",
                description = "API for Basket Service to operate with products in the basket",
                version ="1.0"
        )
)
@RestController
public class BasketController {

    @Autowired
    private Basket basket;

    @Operation(summary="Test method to say hello")
    @GetMapping("/")
    public String get(){
        return "Hello";
    }

    @Operation(summary = "Get all products in the basket with their count", description = "the response includes every product in the basket and it's count")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Returns a Map of all the products in the basket with their count")
    })
    @GetMapping("/basket")
    public Map<Product, Integer> getProductsInBasket(){

        return basket.getAllProductsWithCount();
    }

    @Operation(summary = "Add a product to the basket", description = "if the same product was already added, the number of products will be incremented")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Adds a new product in basket or increases amount of existing")
    })
    @PutMapping("/basket/product")
    public void add(@Parameter(required = true) @RequestBody Product product){
        basket.add(product);
    }

    @Operation(summary = "Delete a product from the basket", description = "Parameter of product id is required. If basked contains several same products, amount will be decreased by 1")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Delete product from the basket or decrease it's amount"),
            @ApiResponse(responseCode="404", description = "Not found product with given id in the basket")
    })
    @DeleteMapping("/basket/product/{id}")
    public void remove(@Parameter(required = true)  @PathVariable String id){
        basket.remove(id);
    }

    @Operation(summary = "Get a product details")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Returns product details (name and price)"),
            @ApiResponse(responseCode="404", description = "Not found product with given id in the basket")
    })
    @GetMapping("/basket/product/{id}")
    public Product getProduct(@Parameter(required = true)  @PathVariable String id){
        return  basket.getProductById(id);
    }

    @Operation(summary = "Calculate total price of the basket",
        responses={
        @ApiResponse(responseCode="200", description = "Returns total price al all the products added in the basket")}
    )
    @GetMapping("/basket/total")   //?? is it RESTful url?
    public Double getTotalPrice(){
        return basket.getTotalPrice();
    }
}
