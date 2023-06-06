package com.bestbuy.storeinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

//@RunWith(SerenityRunner.class)
public class StoresCURDTestWithSteps extends TestBase {

    static String name = "City corner store";
    static String type = "BigBox";
    static String address = "13513 Ridgedale Dr";
    static String address2 = "Stepny Green";
    static String city = "Hopkins";
    static String state = "MN";
    static String zip = "55305";
    static double lat = 44.9696;
    static double lng = 99.449;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int id;
    static String image = TestUtils.getRandomValue();
    @Steps
    StoresSteps storesSteps;

    @Title("This will create a store")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.creatStore(name, type, address, address2, city,
                state, zip, lat, lng, hours);
        response.log().all().body("name", equalTo(name));
        id = response.extract().path("id");

    }

    @Test
    public void test002() {
        ValidatableResponse response = storesSteps.getSingleStore(id).statusCode(200);
        response.log().all().body("id", equalTo(id));

    }

    @Title("This will update the store")

    @Test
    public void test003() {
        storesSteps.updatingStore(id, name, type, address, address2, city, state, zip, lat, lng, hours);
        //HashMap<String, Object> products = storesSteps.getSingleStore(id);
        //Assert.assertThat(, hasValue(id));
    }

    @Test
    public void test004() {
        storesSteps.deleteProduct(id).statusCode(200);
        storesSteps.gettingSinglestore(id).statusCode(404);
    }
}