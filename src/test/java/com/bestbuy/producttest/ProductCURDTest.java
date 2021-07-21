package com.bestbuy.producttest;

import com.bestbuy.stepsinfo.ProductsInfo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SerenityRunner.class)
public class ProductCURDTest extends TestBase {

    static long productid;

    static String name = "Nokia ipod:"  + TestUtils.getRandomValue();
    static String type = "Entertainment:"  + TestUtils.getRandomValue();
    static double price = 13.99;
    static String upc = "1459687694";
    static double shipping = 11.99;
    static String description = "Universal";
    static String manufacturer = "UK power";
    static String model = "String";
    static String url = "String";
    static String image = "String";


    @Steps
    ProductsInfo productsInfo;


    @Title("This will get all products")
    @Test
    public void test001() {
        productsInfo.getAllProducts().log().all().statusCode(200);

    }

    @Title("This will create new product")
    @Test
    public void test002() {
        productid= productsInfo.createNewProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image).statusCode(201).extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("product id is : " + productid);

    }

    @Title("This will get product- By product ID ")
    @Test
    public void test003() {
        productsInfo.getProductByID(productid).log().all().statusCode(200);

    }
    @Title("This will be updated a product by id")
    @Test
    public void test004() {
        name = name + "_Updated";
        price = 99.99;
        upc = upc + "_added";
        productsInfo.updateProduct(productid, name, type, upc, price, description, model).log().all().statusCode(200);
        productsInfo.getProductByID(productid).body("name", equalTo(name));

    }

    @Title("This will delete and verify if product is deleted")
    @Test
    public void test005() {
        productsInfo.deleteProduct(productid).statusCode(200);
        productsInfo.getProductByID(productid).log().all().statusCode(404);
    }

}
