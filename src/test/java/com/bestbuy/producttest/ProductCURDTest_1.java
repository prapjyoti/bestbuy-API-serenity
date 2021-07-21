package com.bestbuy.producttest;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;


import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

public class ProductCURDTest_1 extends TestBase {


    static String name = "Protein Bar"+ TestUtils.getRandomValue();
    static String type = "DryFruit " + TestUtils.getRandomValue();
    static int price = 16;
    static String upc = "Same Day" ;
    static int shipping = 12;
    static String description = "Dates flavour" ;
    static String manufacturer = "Health Score";
    static String model = "family size" ;
    static String url =  "String" ;
    static String image = "String";
    static long productId;


    @Title( "This will create a a new product" )
    @Test
    public void test001(){
       ProductPojo productpojo = new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(price);
        productpojo.setShipping(shipping);
        productpojo.setUpc(upc);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setUrl(url);
        productpojo.setImage(image);
        SerenityRest.rest()
                . given().log().all()
                .header("Content-Type", "application/json")
                .body(productpojo)
                .when()
                .post( EndPoints.CREATE_NEW_PRODUCT)
                .then().log().all();

    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002(){
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";
        HashMap<String,Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                        .then().log().all().statusCode( 200 )
                        .extract()
                        .path( p1 + name + p2 );
        assertThat( value,hasValue( name ) );
        System.out.println(value);
        productId = (long) value.get( "id" );
    }
    @Title( "Update the product information and verify the updated information" )
    @Test
    public void test003() {


        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";
        name = name + "_Updated";


        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);
        SerenityRest.rest()
                .given().log().all()
                .header( "Content-Type","application/json" )
                .pathParam( "id",productId )
                .body(productsPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all().statusCode( 200 );

        HashMap<String,Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get( EndPoints.CREATE_NEW_PRODUCT)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1+ name +p2);
        assertThat(value,hasValue(name));
        System.out.println(value);


    }
    @Title("Delete the PRODUCT and verify if the PRODUCT is deleted!")
    @Test
    public void test004() {
        SerenityRest.rest()
                .given()
                .pathParam( "id",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().statusCode( 204 );

        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode( 404 );
    }


}
