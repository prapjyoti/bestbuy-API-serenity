package com.bestbuy.stepsinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;



public class ProductsInfo {


    @Step("Creating new product with name: {0}, type:{1}, price:{2}, upc:{3}, shipping:{4}, description:{5}, manufacture:{6}, model{7}, url:{8} and image:{9}")
    public ValidatableResponse createNewProduct(String name, String type, double price, String upc, double shipping, String description, String manufacturer, String model,
                                                String url, String image) {


        ProductPojo productPojo = new ProductPojo();

        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(productPojo)
                .post(EndPoints.CREATE_NEW_PRODUCT)
                .then();

    }

    //*This  is a  GET method which on request - brings all Product information

    @Step("Getting all products")
    public ValidatableResponse getAllProducts() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }

    //This method brings product by id
    @Step("Getting product with single productid:{0}")
    public ValidatableResponse getProductByID(long productid) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", productid)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting product information with product id: {0}")
    public ValidatableResponse deleteProduct(long productid) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", productid)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Updating Product information with productId : {0} name : {1} , type : {2} , upc : {3} , price : {4} , description : {5} , model : {6} ")

    public ValidatableResponse updateProduct(long productid,String name , String type , String upc , double price , String description , String model){

       ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setUpc(upc);
        productsPojo.setPrice(price);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);

        return  SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .pathParam("id",productid)
                .log().all()
                .when()
                .body(productsPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID )
                .then();
    }
    }

