package com.bestbuy.storetest;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.stepsinfo.StoresInfo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SerenityRunner.class)
public class StoresCURDTest extends TestBase {

    static String name = "Money Transfer Store:"  + TestUtils.getRandomValue();
    static String type = "PawnBroker:" + TestUtils.getRandomValue();
    static String address = "91 Al Gate";
    static String address2 = "Baker Street";
    static String city = "London";
    static String state = "North";
    static String zip = "52525AB";
    static float lat = 23.123f;

    static long storeId;


    @Steps
    StoresInfo storesInfo;

    @Title("This test will create a new stores and verify it")
    @Test
    public void test001() {
        storeId = storesInfo.createNewStore(name, type, address, address2, city, state, zip, lat).statusCode(201).log().all().extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("store id is : " + storeId);
    }

    @Title("This test will get the stores information by ID")
    @Test
    public void test002() {
        storesInfo.getStoreById(storeId).statusCode(200).log().all();
    }
    @Title("This test will update the store information and verify it")
    @Test

    public void test003() {
        name = name +"_Updated";
        type = type +"_Updated";

        storesInfo.updateStore(storeId,name, type).statusCode(200).log().all();
        storesInfo.getStoreById(storeId).body("name",equalTo(name));

    }

    @Title("This test will delete the store and verify the store is deleted ")
    @Test
    public void test004() {
        storesInfo.deleteStore(storeId).statusCode(200).log().all();
        storesInfo.getStoreById(storeId).statusCode(404).log().all();
    }
    @Title("This will get all stores")
    @Test
    public void test005() {
       storesInfo.getAllStores().log().all().statusCode(200);

    }
}
