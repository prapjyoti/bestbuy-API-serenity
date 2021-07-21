package com.bestbuy.constants;

public class EndPoints {

 //*This is the EndPoint of BestBuy Product
    //


    public static final String GET_ALL_PRODUCTS="/products";
    public static final String CREATE_NEW_PRODUCT="/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID="/products/{id}";
    public static final String UPDATE_PRODUCT_BY_ID="/products/{id}";
    public static final String DELETE_PRODUCT_BY_ID="/products/{id}";


    //This Is the EndPoint of BestBuy Store
    public static final String GET_ALL_STORES="/stores";
    public static final String CREATE_NEW_STORE="/stores";
    public static final String GET_STORE_BY_ID="/stores/{id}";
    public static final String UPDATE_STORE_BY_ID="/stores/{id}";
    public static final String DELETE_STORE_BY_ID="/stores/{id}";

   //These Are Endpoints of Services
    public static final String GET_ALL_SERVICES = "/services";
    public static final String CREATE_SERVICES = "/services";
    public static final String GET_SERVICES_BY_ID = "/services/{id}";
    public static final String UPDATE_SERVICES_BY_ID = "/services/{id}";
    public static final String DELETE_SERVICES = "/services/{id}";

//These are Endpoints of Categories
    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String CREATE_CATEGORIES = "/categories";
    public static final String GET_CATEGORIES_BY_ID = "/categories/{id}";
    public static final String UPDATE_CATEGORIES_BY_ID = "/categories/{id}";
    public static final String DELETE_CATEGORIES_BY_ID = "/categories/{id}";

}
