package com.bestbuy.productsinfo;

import com.bestbuy.prodcutsinfo.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

//@RunWith(SerenityRunner.class)
public class ProductsCRUDTestWithSteps extends TestBase {
    static String name = "Acer-Chromebook" + TestUtils.getRandomValue();
    static String type = "laptop";
    static double price = 1669.89;
    static double shipping = 5.99;
    static String upc = TestUtils.getRandomValue();
    static String description = "The stylish Chromebook";
    static String manufacturer = "Acer";
    static String model = TestUtils.getRandomValue();
    static String url = "https://pisces.bbystatic.com/" + TestUtils.getRandomValue() + ".jpg;maxHeight=640;maxWidth=550";
    static String image = TestUtils.getRandomValue();
    static int id;
    @Steps
    ProductsSteps productsSteps;

    @Title("this will create the product")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProducts(name, type, price, shipping, upc, description, model, manufacturer, url, image);
        response.log().all().body("name", equalTo(name));
        id = response.extract().path("id");

    }

    @Title("get the product by id ")
    @Test
    public void test002() {
        ValidatableResponse response = productsSteps.getSingleProduct(id);
        response.log().all().body("id", equalTo(id));
    }

    @Title("This will update the product by id ")
    @Test
    public void test003() {
        name = "Duracell Plus - AAA Batteries (12-Pack)";
        ValidatableResponse response = productsSteps.updateTheProduct(id, name, type, price, (int) shipping, upc, description, model, manufacturer
                , url, image);
        response.log().all().body("name", equalTo(name));
    }

    @Test
    public void test004() {
        productsSteps.deleteProduct(id).statusCode(200);
        productsSteps.gettingSingleProduct(id).statusCode(404);

    }
}
