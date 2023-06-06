package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.StorePogo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoresSteps {
    @Step("Creating store with name : {0},type :{1},address :{2},address2:{3},city :{4},state :{5}" +
            ",zip:{6},lat :{7},lng :{8},hours :{9}")
    public ValidatableResponse creatStore(String name, String type, String address,String address2,String city,String state,
                                          String zip, double lat, double lng,String hours){
        StorePogo storePojo= StorePogo.getStorePojo(name,type,address,address2,city,state,zip,lat,lng,hours);
       return SerenityRest.given().log().all()
               .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post(Path.stores)
                .then().log().all();

    }
    @Step("Getting the store information with id : {0}")
    public ValidatableResponse getSingleStore(int id) {
        return SerenityRest.given().log().all()
                .pathParam("storeID", id)
                .when()
              //  .body(storePojo)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().log().all();

    }
    @Step("Updating the store with id: {0},type :{1},address :{2},address2:{3},city :{4},state :{5},zip:{6},lat :{7},lng :{8},hours :{9}")
    public ValidatableResponse updatingStore(int id,String name, String type, String address,String address2,String city,String state,
                               String zip, double lat, double lng,String hours){
        StorePogo storePojo = new StorePogo();
        storePojo.setName("CityFoods store");
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParams("storeID", id)
                .body(storePojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all().statusCode(200);
    }
    @Step("Deleting store storeId: {0}")
    public ValidatableResponse deleteProduct(int studentId){
        return SerenityRest.given().log().all()
                .pathParam("storeID", studentId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }
    @Step("Getting single store by id : {0}")
    public ValidatableResponse gettingSinglestore(int id) {
        return SerenityRest.given()
                .pathParams("storeID", id)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }

}
