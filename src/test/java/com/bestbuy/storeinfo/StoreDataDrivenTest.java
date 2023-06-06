package com.bestbuy.storeinfo;

import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent(threads = "4x")
@UseTestDataFrom("src/test/java/resources/testdata/storeinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class StoreDataDrivenTest extends TestBase {
    @Steps
    StoresSteps storeSteps;
    private String name;
    private String type;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private double lat;
    private  double lng;
    private String hours;


    @Title("Data Driven Test for adding multiple stores to the application")
    @Test
    public void createMultipleStores() {
        storeSteps.creatStore(name,type,address,address2,city,state,zip,lat,lng,hours).statusCode(201);
    }
}

