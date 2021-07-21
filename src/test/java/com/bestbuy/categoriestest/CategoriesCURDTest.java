package com.bestbuy.categoriestest;

import com.bestbuy.stepsinfo.CategoriesInfo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SerenityRunner.class)
public class CategoriesCURDTest extends TestBase {


    static String name = "Beauty Salon" + TestUtils.getRandomValue();
    static String id = "BT005" + TestUtils.getRandomValue();
    static String categoriesId;

    @Steps
    CategoriesInfo categoriesInfo;

    @Title("This test will create a new categories and verify by created categories")
    @Test

    public void test001() {

        categoriesId = categoriesInfo.createNewCategories(name, id).log().all().statusCode(201).extract().response()
                .body().jsonPath().getString("id");
        System.out.println("categories ID is" + categoriesId);
    }

    @Title("This test will get the categories information by ID")
    @Test

    public void test002() {
        categoriesInfo.getCategoriesById(categoriesId).log().all().statusCode(200);

    }

    @Title("This test will update the categories information and verify the updated information")
    @Test

    public void test003() {

        name = name + "_Updated";

        categoriesInfo.updateCategories(categoriesId, name).statusCode(200).log().all();
        categoriesInfo.getCategoriesById(categoriesId).body("name", equalTo(name));
    }

    @Title("This test will delete the categories information and verify the categories is deleted ")
    @Test

    public void test004() {
        categoriesInfo.deleteCategoriesById(categoriesId).statusCode(200).log().all();
        categoriesInfo.getCategoriesById(categoriesId).statusCode(404).log().all();
    }

    @Title("This will get all categories")
    @Test
    public void test005() {
        categoriesInfo.getAllCategories().log().all().statusCode(200);

    }
}
