package com.bestbuy.productsinfo;

import com.bestbuy.prodcutsinfo.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent(threads = "8x")
@UseTestDataFrom("src/test/java/resources/testdata/productinfo.csv")
@RunWith(SerenityParameterizedRunner.class)

public class ProductsDataDrivenTest extends TestBase {
    @Steps
    ProductsSteps productSteps;
    private String name;
    private String type;
    private double price;
    private double shipping;
    private String upc;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;
    @Title("Data Driven Test for adding multiple products to the application")
    @Test
    public void createMultipleProducts(){
        productSteps.createProducts(name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(201);
    }
}

