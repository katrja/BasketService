package org.example;

import org.assertj.core.api.Assertions;
import org.example.model.Basket;
import org.example.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(BasketController.class)
public class BasketTest {

    private Map<Product, Integer> testProducts = new HashMap<>();
    {
        Product product1 = new Product("knife", 50.0);
        product1.setId();

        testProducts.put(product1 , 1);
        testProducts.put(new Product("forks set 6", 20.50), 2);
    }

    //@InjectMocks
    private Basket basket = new Basket();

    @Before
    public void init() {
        basket.setProducts(testProducts);
        //MockitoAnnotations.openMocks(this);
      //  Mockito.when(basket.getAllProductsWithCount()).thenReturn(testProducts);
    }

    @Test
    public void getAllProductsWithCount() {
        //
        Map<Product, Integer> result = basket.getAllProductsWithCount();

        assertEquals(2,result.size());
        assertTrue(result.equals(testProducts));
    };

    @Test
    public void getProductById(){

        basket.getProductById()
    }
}
