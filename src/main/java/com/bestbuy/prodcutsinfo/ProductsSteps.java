package com.bestbuy.prodcutsinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.ProductsaPogo;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductsSteps {
    @Step("Getting the product information with id : {0}")
    public ValidatableResponse getSingleProduct(int id) {
        return SerenityRest.given().log().all()
                .pathParams("productID", id)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCTS_BY_ID)
                .then().log().all();


}
    @Step("Creating product with name : {0},type :{1},price :{2},shipping:{3},upc :{4},description :{5}" +
            ",model:{6},manufacturer :{7},url :{8},image :{9}")
    public ValidatableResponse createProducts(String name, String type, double price,
                                              double shipping, String upc, String description,
                                              String model, String manufacturer, String url,
                                              String image){
        ProductsaPogo productsPogo = ProductsaPogo.getProductPojo(name,type,price,shipping,upc,description,model,manufacturer,url,image);
       return SerenityRest.given().log().all()
              //  .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .when()
                .body(productsPogo)
                .post(Path.products)
                .then();

    }
    @Step("Updating the store with id: {0}, name : {1}, type: {2}, price: {3},shipping {4}, upc {5}, description: {6}, model {7}, manufacturer: {8}, url {9}\\\" +\\n\" +\n" +
            "image : {10}")
    public ValidatableResponse updateTheProduct(int id, String name, String type, double price,
                                                int shipping, String  upc, String description,
                                                String model, String manufacturer, String url,
                                                String image ){

        ProductsaPogo productPojo=new ProductsaPogo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(TestUtils.getRandomValue());
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(TestUtils.getRandomValue());
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParams("productID",id)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCTS_BY_ID)
        .then().log().all().statusCode(200);



    }
    @Step("Deleting product information with productsId: {0}")
    public ValidatableResponse deleteProduct(int id){
        return SerenityRest.given().log().all()
                .pathParam("productID", id)
                .when()
                .delete(EndPoints.DELETE_PRODUCTS_BY_ID)
                .then();
    }
    @Step("Getting single : {0}")
    public ValidatableResponse gettingSingleProduct(int id) {
        return SerenityRest.given()
                .pathParams("productID", id)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCTS_BY_ID)
                .then();
    }



}
