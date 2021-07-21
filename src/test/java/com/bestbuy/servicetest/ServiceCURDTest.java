package com.bestbuy.servicetest;

import com.bestbuy.stepsinfo.ServicesInfo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SerenityRunner.class)
public class ServiceCURDTest extends TestBase {


    static String name = "Hand Car Wash"  +  TestUtils.getRandomValue();
    static long servicesId;

    @Steps
    ServicesInfo servicesInfo;
    @Title("This test will create a new services and verify it")
    @Test

    public void test001() {

        servicesId = servicesInfo.createNewServices(name).log().all().statusCode(201).extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("services ID is:" + servicesId);
    }
    @Title("This test will get the services information by ID")
    @Test

    public void test002() {
       servicesInfo.getServicesById(servicesId).log().all().statusCode(200).log().all();

    }

    @Title("This test will update the services information and verify Update")
    @Test

    public void test003(){

        name = name+"_Updated";

       servicesInfo.updateServices(servicesId,name).statusCode(200).log().all();
        servicesInfo.getServicesById(servicesId).body("name",equalTo(name));

    }
    @Title("This test will delete the categories information and verify the categories is deleted ")
    @Test

    public void test004(){
        servicesInfo.deleteServicesById(servicesId).statusCode(200).log().all();
        servicesInfo.getServicesById(servicesId).statusCode(404).log().all();
    }
    @Title("This will get all services")
    @Test
    public void test005() {
       servicesInfo.getAllServices().log().all().statusCode(200);

    }
}


